/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.batch;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.zhouyp.batch.entity.Message;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Writer;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

/**
 * <B>主类名称：</B>MessageJob<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/7/1 上午 9:26
 */
@Component
public class MessageJob {
    final static private int CHUNK_SIZE=1;
    final static private int SKIP_LIMIT=1;
    final static private String MESSAGE_FILE="C:\\Users\\XS-1009387\\AppData\\Roaming\\JetBrains\\IntelliJIdea2021.3\\scratches\\message.csv";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job messageMigrationJob(@Qualifier("messageMigrationStep") Step messageMigrationStep) {
        return jobBuilderFactory.get("messageMigrationJob")
                .start(messageMigrationStep)
                .build();
    }
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step messageMigrationStep(@Qualifier("jsonMessageReader") FlatFileItemReader<Message> jsonMessageReader,
                                     @Qualifier("messageItemWriter") JpaItemWriter<Message> messageItemWriter,
                                     @Qualifier("errorWriter") Writer errorWriter) {
        return stepBuilderFactory.get("messageMigrationStep")
                .<Message, Message>chunk(CHUNK_SIZE)
                .reader(jsonMessageReader).faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageItemReadListener(errorWriter))
                .writer(messageItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageWriteListener())
                .build();
    }
    @Bean
    public FlatFileItemReader<Message> jsonMessageReader() {
        FlatFileItemReader<Message> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(new File(MESSAGE_FILE)));
        reader.setLineMapper(new LineMapper<Message>(){
            private MappingJsonFactory factory = new MappingJsonFactory();
            @Override
            public Message mapLine(String line, int lineNumber) throws Exception {
                JsonParser parser = factory.createParser(line);
                Map<String, Object> map = (Map) parser.readValueAs(Map.class);
                Message message = new Message();
                return message;
            }
        });
        return reader;
    }
    @Autowired
    private EntityManagerFactory entityManager;

    @Bean
    public JpaItemWriter<Message> messageItemWriter() {
        JpaItemWriter<Message> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return writer;
    }
}
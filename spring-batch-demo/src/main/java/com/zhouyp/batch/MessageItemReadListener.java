package com.zhouyp.batch;

import com.zhouyp.batch.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

import java.io.IOException;
import java.io.Writer;

@Slf4j
public class MessageItemReadListener implements ItemReadListener<Message> {
    private Writer errorWriter;
 
    public MessageItemReadListener(Writer errorWriter) {
        this.errorWriter = errorWriter;
    }
 
    @Override
    public void beforeRead() {
    }
 
    @Override
    public void afterRead(Message item) {
    }
 
    @Override
    public void onReadError(Exception ex) {
        try {
            errorWriter.write(String.format("%s%n", ex.getMessage()));
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
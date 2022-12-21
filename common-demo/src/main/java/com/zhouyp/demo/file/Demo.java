/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.file;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <B>主类名称：</B>Demo<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/7/18 下午 4:42
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("test.txt");
        final Stream<String> stream = Files.readAllLines(path).stream();
        Files.write(path, "".getBytes());
        Files.delete(path);
        final FileSystem aDefault = FileSystems.getDefault();
    }
}
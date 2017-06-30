/*
 *
 * Copyright (C) 1999-2016 IFLYTEK Inc.All Rights Reserved.
 * History：
 * Version   Author      Date                              Operation
 * 1.0       yaochen4    2017/6/30                           Create
 */
package com.piggsoft.mysql1;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author yaochen4
 * @version 1.0
 * @create 2017/6/30
 * @since 1.0
 */
public class MainTest {

    @Test
    public void testReadFile() throws IOException {
        final Path source = Paths.get("E:\\Github\\JMPjct\\src\\com");
        Path target = Paths.get("E:\\Github\\parser\\src\\main\\java");

        final Path dest = target.resolve(source.getFileName());

        Files.walkFileTree(source, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // 在目标文件夹中创建dir对应的子文件夹
                Path subDir = 0 == dir.compareTo(source) ? dest : dest.resolve(dir.subpath(source.getNameCount(), dir.getNameCount()));
                Files.createDirectories(subDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path subDir = 0 == file.compareTo(source) ? dest : dest.resolve(file.subpath(source.getNameCount(), file.getNameCount()));
                Files.createFile(subDir);
                List<String> list = Files.readAllLines(file, Charset.forName("UTF-8"));
                Files.write(subDir, list, Charset.forName("UTF-8"), StandardOpenOption.WRITE);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

}

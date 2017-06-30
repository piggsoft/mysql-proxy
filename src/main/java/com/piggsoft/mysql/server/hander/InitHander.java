/*
 *
 * Copyright (C) 1999-2016 IFLYTEK Inc.All Rights Reserved.
 * History：
 * Version   Author      Date                              Operation
 * 1.0       yaochen4    2017/6/30                           Create
 */
package com.piggsoft.mysql.server.hander;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yaochen4
 * @version 1.0
 * @create 2017/6/30
 * @since 1.0
 */
public class InitHander extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        if (ch.isWritable()) {
            ch.write("123123");
            ch.flush();
        }

        ch.pipeline().addLast(new DiscardServerHandler());
    }
}

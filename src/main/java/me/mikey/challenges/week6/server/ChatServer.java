package me.mikey.challenges.week6.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Michael on 14/11/16.
 */
public class ChatServer {
    public static final int PORT = 8080;

    public ChatServer() {
        NioEventLoopGroup acceptorGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup handlerGroup = new NioEventLoopGroup(10);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(acceptorGroup, handlerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChatSocketInitialiser());

            try {
                System.out.println("Starting chat server on port " + PORT + "!");
                ChannelFuture f = bootstrap.bind(PORT).sync();
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            acceptorGroup.shutdownGracefully();
            handlerGroup.shutdownGracefully();
        }
    }
}

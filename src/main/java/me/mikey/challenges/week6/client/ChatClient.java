package me.mikey.challenges.week6.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Michael on 14/11/16.
 */
public class ChatClient {
    private String host;
    private String username;

    public ChatClient(String[] args) {

        if(args.length < 2) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter an IP");
            this.host = scanner.nextLine();

            System.out.println("Enter a username");
            this.username = scanner.nextLine();
        } else {
            this.host = args[0];
            this.username = args[1];

            if(args.length > 2 && args[2].equalsIgnoreCase("auto")) {
                for(int i = 0; i < 100; i++) {
                    final int finalI = i;
                    new Thread() {
                        @Override
                        public void run() {
                            new ChatClient("127.0.0.1", username + finalI);
                        }
                    }.start();
                }
            }
        }

        start();
    }

    public ChatClient(String host, String username) {
        this.host = host;
        this.username = username;
        start();
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            ChatClient self = this;

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new ChatClientHandler(self));
                        }
                    });

            //todo add ip input
            Channel channel = b.connect(host, 8080).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            channel.writeAndFlush("USER " + username + "\r\n");

            while(true) {
                String line = in.readLine();

                if(!line.startsWith("/")) {
                    channel.writeAndFlush("MSG " + line + "\r\n");
                }
                else if(line.startsWith("/user")) {
                    if(line.split(" ").length <= 1) {
                        System.out.println("Invalid command syntax! /user <name>");
                    } else {
                        channel.writeAndFlush("USER " + line.split(" ")[1] + "\r\n");
                    }
                }

                if(line.equals("bye"))
                    return;
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

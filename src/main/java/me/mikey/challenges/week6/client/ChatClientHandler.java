package me.mikey.challenges.week6.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Michael on 14/11/16.
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    private ChatClient client;

    public ChatClientHandler(ChatClient client) {
        this.client = client;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //TODO split into proper packets etc and deal with in individual classes

        if(msg.startsWith("MSG ")) {
            System.out.println(msg.substring(4));
        }

        if(msg.startsWith("USER ")) {
            client.setUsername(msg.substring(5));
        }

        if(msg.startsWith("ERR ")) {
            System.err.println(msg.substring(4));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

package me.mikey.challenges.week6.server;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Michael on 14/11/16.
 */
public class ChatUser {
    private static int userCount = 0;

    private String username;
    private ChannelHandlerContext ctx;

    public ChatUser(ChannelHandlerContext ctx) {
        this.username = "Anon" + (userCount++);
        this.ctx = ctx;
    }

    public void send(String msg) {
        ctx.writeAndFlush(msg + "\r\n");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

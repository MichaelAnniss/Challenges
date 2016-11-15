package me.mikey.challenges.week6.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import me.mikey.challenges.week6.*;

/**
 * Created by Michael on 12/11/16.
 */
@ChannelHandler.Sharable
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ChatUser user = new ChatUser(ctx);
        ctx.channel().attr(Week6.USER_KEY).set(user);

        channels.add(ctx.channel());
        broadcast("MSG * - " + user.getUsername() + " has joined!");
        broadcast("MSG * - There are now " + channels.size() + " users connected");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        broadcast("MSG * - " + ctx.channel().attr(Week6.USER_KEY).get().getUsername() + " has left");
        ctx.channel().attr(Week6.USER_KEY).set(null);
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        ChatUser u = ctx.channel().attr(Week6.USER_KEY).get();

        if(s.startsWith("USER")) {
            String username = s.substring(5);

            if(!username.matches("[a-zA-Z0-9]+") || username.length() > 16) {
                u.send("ERR Invalid username!");
                return;
            }

            for(Channel ch : channels) {
                ChatUser user = ch.attr(Week6.USER_KEY).get();

                if(ch != ctx.channel() && user.getUsername().equals(username)) {
                    u.send("ERR That username is already in use!");
                    return;
                }
            }

            u.send("USER " + username);
            broadcast("MSG " + u.getUsername() + " is now known as " + username);
            u.setUsername(username);
        }

        else if(s.startsWith("MSG")) {
            String msg = s.substring(4);

            if(!msg.matches("[a-zA-Z0-9_*+?!@£\"'(){}&^%$\\s]+") || msg.length() == 0) {
                u.send("ERR Invalid message!");
                return;
            }

            broadcast("MSG " + u.getUsername() + "> " + msg);
        }

        else {
            System.out.println("[ERROR] Unknown command " + s);
        }
    }

    public void broadcast(String msg) {
        System.out.println("Broadcasting " + msg);
        for(Channel channel : channels) {
            channel.writeAndFlush(msg + "\r\n");
        }
    }
}

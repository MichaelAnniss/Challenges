package me.mikey.challenges.week6;

import io.netty.util.AttributeKey;
import me.mikey.challenges.week6.client.ChatClient;
import me.mikey.challenges.week6.server.ChatServer;
import me.mikey.challenges.week6.server.ChatUser;

import java.util.Scanner;

/**
 * Created by Michael on 11/11/16.
 */
public class Week6 {
    public static final AttributeKey<ChatUser> USER_KEY = AttributeKey.valueOf("irc-user");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Client or server?");
        String choice = scanner.nextLine();

        if(choice.equals("s"))
            new ChatServer();
        else
            new ChatClient(args);
    }
}

package me.mikey.challenges.week8;

import me.mikey.challenges.week8.items.StickItem;
import me.mikey.challenges.week8.items.weapons.SwordItem;
import me.mikey.challenges.week8.items.MapItem;
import me.mikey.challenges.week8.models.Monster;
import me.mikey.challenges.week8.models.Player;
import me.mikey.challenges.week8.models.Room;
import me.mikey.challenges.week8.models.World;

/**
 * Created by Michael on 06/12/2016.
 */
public class Week8 {
    public static void main(String[] args) {
        // Some set up stuff, just set room 1 to be above room 2 which is above room 3
        Room room1 = new Room("Room 1", "Room 1 description");
        Room room2 = new Room("Room 2", "Room 2 description");
        Room room3 = new Room("Room 3", "Room 3 description");
        Room room1e = new Room("East of Room 1", "Room 1 east description");
        Room room1w = new Room("West of Room 1", "Room 1 west description");
        Room room1n = new Room("North of Room 1", "Room 1 north description");

        room1.attachBidirectionally(RoomDirection.SOUTH, room2);
        room2.attachBidirectionally(RoomDirection.SOUTH, room3);
        room1.attachBidirectionally(RoomDirection.EAST, room1e);
        room1.attachBidirectionally(RoomDirection.NORTH, room1n);
        room1.attachBidirectionally(RoomDirection.WEST, room1w);

        // Add some items
        room1.addItem(new MapItem());
        room2.addItem(new SwordItem());
        room3.addItem(new StickItem());

        Monster monster1 = new Monster("Monster 1", 10, 1);
        Monster monster2 = new Monster("Monster 2", 15, 2);
        Monster monster3 = new Monster("Monster 3", 20, 3);

        room1.addMonster(monster1);
        room2.addMonster(monster2);
        room3.addMonster(monster3);

        Player.getInstance().setCurrentRoom(room1);
        Player.getInstance().displayState();

        System.out.println("What do you want to do?");

        while(true) {
            World.getInstance().getParser().readLine();

            if(World.getInstance().isFinishing()) {
                System.out.println("Thank you for playing!");
                return;
            }

            for(int i = 0; i < 3; i++)
                System.out.println();

            Player.getInstance().displayState();

            System.out.println("\nWhat do you want to do?");
        }
    }
}

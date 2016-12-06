package me.mikey.challenges.week8.items;

import me.mikey.challenges.week8.RoomDirection;
import me.mikey.challenges.week8.models.Item;
import me.mikey.challenges.week8.models.Player;
import me.mikey.challenges.week8.models.Room;
import me.mikey.challenges.week8.models.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 06/12/2016.
 */
public class MapItem extends Item {

    private List<Room> processedRooms = new ArrayList<>();

    public MapItem() {
        super("Map");
    }

    @Override
    public void use() {
        System.out.println("Using the map...");

        Room currentRoom = Player.getInstance().getCurrentRoom();

        displayRoom(currentRoom, 0);
        processedRooms.clear();
    }

    public void displayRoom(Room room, int level) {
        for(RoomDirection direction : RoomDirection.values()) {
            Room dirRoom = room.getRoom(direction);

            if(dirRoom != null && !processedRooms.contains(dirRoom)) {
                for(int i = 0; i < level; i++) {
                    System.out.print("  ");
                }
                System.out.println(direction + ": " + dirRoom.getName());
                processedRooms.add(room);
                displayRoom(dirRoom, level + 1);
            }
        }
    }
}

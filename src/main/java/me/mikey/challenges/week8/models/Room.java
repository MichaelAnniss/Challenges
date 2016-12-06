package me.mikey.challenges.week8.models;

import me.mikey.challenges.week8.RoomDirection;
import me.mikey.challenges.week8.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 06/12/2016.
 */
public class Room {
    private String name;
    private String description;
    private Map<RoomDirection, Room> connectedRooms = new HashMap<>();
    private List<Item> itemsOnFloor = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void attachRoom(RoomDirection direction, Room room) {
        if(this.connectedRooms.containsKey(direction))
            throw new IllegalArgumentException("Room already exists in direction " + direction);

        this.connectedRooms.put(direction, room);
    }

    public void attachBidirectionally(RoomDirection direction, Room room) {
        attachRoom(direction, room);
        room.attachRoom(direction.getOpposite(), this);
    }

    public Room move(RoomDirection direction) throws InvalidMoveException {
        if(this.monsters.size() > 0) {
            //Cannot move, there are monsters in the room!
            throw new InvalidMoveException("There are monsters in the room you are in! You cannot leave!");
        }

        return this.connectedRooms.get(direction);
    }

    public Room getRoom(RoomDirection direction) {
        return this.connectedRooms.get(direction);
    }

    public void addItem(Item item) {
        this.itemsOnFloor.add(item);
    }

    public void addMonster(Monster monster) {
        monster.setRoom(this);
        this.monsters.add(monster);
    }

    public void removeMonster(Monster monster) {
        this.monsters.remove(monster);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItemsOnFloor() {
        return itemsOnFloor;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}

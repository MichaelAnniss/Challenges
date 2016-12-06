package me.mikey.challenges.week8.models;

import me.mikey.challenges.week8.InputParser;
import me.mikey.challenges.week8.parsers.SimpleInputParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 06/12/2016.
 */
public class World {
    private static World instance = new World();

    private List<Room> allRooms = new ArrayList<>();
    private InputParser parser = new SimpleInputParser();
    private boolean finishing;

    private World() {}

    public void addRoom(Room room) {
        this.allRooms.add(room);
    }

    public List<Room> getAllRooms() {
        return allRooms;
    }

    public InputParser getParser() {
        return parser;
    }

    public boolean isFinishing() {
        return finishing;
    }

    public void setFinishing(boolean finishing) {
        this.finishing = finishing;
    }

    public static World getInstance() {
        return instance;
    }
}

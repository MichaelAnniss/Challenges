package me.mikey.challenges.week8.models;

import me.mikey.challenges.week8.RoomDirection;
import me.mikey.challenges.week8.exceptions.InvalidMoveException;
import me.mikey.challenges.week8.items.WeaponItem;
import me.mikey.challenges.week8.items.weapons.HandWeaponItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Michael on 06/12/2016.
 */
public class Player {
    private static Player instance = new Player();

    private Room currentRoom;
    private List<Item> inventory = new ArrayList<>();
    private WeaponItem currentWeapon = new HandWeaponItem();
    private int health = 100;

    private Player() {}

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void moveInDirection(RoomDirection direction) throws InvalidMoveException {
        if(currentRoom == null)
            throw new IllegalArgumentException("Player does not have a room!");

        Room newRoom = currentRoom.move(direction);

        if(newRoom == null) {
            throw new InvalidMoveException("You cannot move " + direction.name() + "!");
        }

        currentRoom = newRoom;
    }

    public void displayState() {
        if(currentRoom != null) {
            int health = this.health / 10;

            for(int i = 0; i < health; i++) {
                System.out.print("♥");
            }

            for(int i = 0; i < 10 - health; i++) {
                System.out.print("♡");
            }

            System.out.println();
            System.out.println(" ============= ");
            System.out.println("You are in " + currentRoom.getName() + "!");
            System.out.println(currentRoom.getDescription());
            System.out.println(" ============= ");

            if(currentRoom.getMonsters().size() > 0) {
                System.out.println("!!!! Monsters on this floor! " + currentRoom.getMonsters().stream().map(
                        m -> m.getName() + " (" + m.getHealth() + " / " + m.getMaxHealth() + ")"
                ).collect(Collectors.joining(", ")));
            }

            System.out.println("You are currently using " + currentWeapon.getName());
            System.out.println("Inventory: " + inventory.stream().map(Item::getName).collect(Collectors.joining(", ")));
        } else {
            System.out.println("Invalid room!");
        }
    }

    public void damage(int amount) {
        this.health -= amount;

        if(this.health < 0) {
            die();
        }
    }

    public void die() {
        System.out.println("Game over! You died!");
        System.exit(0); // bad idea
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public WeaponItem getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(WeaponItem currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public static Player getInstance() {
        return instance;
    }
}

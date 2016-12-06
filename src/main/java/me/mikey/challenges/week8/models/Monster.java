package me.mikey.challenges.week8.models;

/**
 * Created by Michael on 06/12/2016.
 */
public class Monster {
    private String name;
    private int maxHealth;
    private int health;
    private int damage;
    private Room room;

    public Monster(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }

    public void damage(int amount) {
        this.health -= amount;

        if(this.health <= 0) {
            die();
        }
    }

    public void die() {
        room.removeMonster(this);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}

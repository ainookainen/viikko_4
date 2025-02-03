package main;

import java.io.Serializable;

public class Monster implements Serializable {
    private String type;
    private int health;

    public Monster(String type, int health) {
        this.type = type;
        this.health = health;
    }

    public void printInfo(int number) {
        System.out.println(number + ": " + this.type + " / " + this.health + "HP");
    }

    public boolean takeDamage(int dmg) {
        this.health -= dmg;
        if (this.health <= 0) {
            return false;
        } else {
            System.out.println("Hirviöllä on " + this.health + " elämää jäljellä.");
            return true;
        }
    }

    public boolean isDead() {
        if (this.health <= 0) {
            System.out.println(this.type + " on kuollut!");
            return true;
        } else {
            return false;
        }
    }

    public String getType() {
        return this.type;
    }
}

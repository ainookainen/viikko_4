package main;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void attack(Monster monster) {
        System.out.println(this.name + " hyökkää " + monster.getType() + " hirviöön!");
        monster.takeDamage(10);
    }

    public String getName() {
        return this.name;
    }
}

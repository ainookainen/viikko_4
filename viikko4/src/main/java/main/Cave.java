package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Cave implements Serializable {
    private ArrayList<Monster> monsters = new ArrayList<>();
    Player player;

    public Cave(Player player) {
        this.player = player;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void listMonsters() {
        int i = 1;
        for (Monster monster : monsters) {
            monster.printInfo(i++);
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Monster getMonsterById(int id) {
        return monsters.get(id);
    }

    public void saveGame(String filename) {
        try (ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(filename))) {
            fileWriter.writeObject(this);
            fileWriter.close();
            System.out.println("Peli tallennettiin tiedostoon " + filename + ".");
        } catch (IOException e) {
            System.out.println("Pelin tallentaminen ei onnistunut.");
            e.printStackTrace();
        }
    }

    public static Cave loadGame(String filename) {
        try {
            ObjectInputStream gameReader = new ObjectInputStream(new FileInputStream(filename));
            Cave cave = (Cave) gameReader.readObject();
            gameReader.close();
            return cave;
        } catch (FileNotFoundException e) {
            System.out.println("Pelin lataaminen ei onnistunut.");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("Pelin lataaminen ei onnistunut.");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Pelin lataaminen ei onnistunut.");
            e.printStackTrace();
            return null;
        }
    }
}
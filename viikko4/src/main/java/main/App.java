package main;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Anna pelaajan nimi:");
        String name = sc.nextLine();
        Player newPlayer = new Player(name);
        Cave cave = new Cave(newPlayer);
        boolean exit = false;
        while (!exit) {
            System.out.println(
                    "1) Lisää luolaan hirviö\n2) Listaa hirviöt\n3) Hyökkää hirviöön\n4) Tallenna peli\n5) Lataa peli\n0) Lopeta peli");
            if (sc.hasNext()) {
                int i = 0;
                String stringInput = sc.nextLine();
                i = Integer.parseInt(stringInput);
                switch (i) {
                    case 1:
                        System.out.println("Anna hirviön tyyppi:");
                        String type = sc.nextLine();
                        System.out.println("Anna hirviön elämän määrä numerona:");
                        int health = Integer.parseInt(sc.nextLine());
                        Monster newMonster = new Monster(type, health);
                        cave.addMonster(newMonster);
                        break;
                    case 2:
                        System.out.println("Luolan hirviöt:");
                        cave.listMonsters();
                        break;
                    case 3:
                        int dmg = 10;
                        System.out.println("Valitse hirviö, johon hyökätä:");
                        cave.listMonsters();
                        int id = Integer.parseInt(sc.nextLine()) - 1;
                        Monster targetMonster = cave.getMonsterById(id);
                        cave.player.attack(targetMonster);
                        if (!targetMonster.takeDamage(dmg)) {
                            cave.getMonsters().remove(targetMonster);
                        }
                        break;
                    case 4:
                        System.out.println("Anna tiedoston nimi, johon peli tallentaa:");
                        String saveFileName = sc.nextLine();
                        cave.saveGame(saveFileName);
                        break;
                    case 5:
                        System.out.println("Anna tiedoston nimi, josta peli ladataan:");
                        String loadFileName = sc.nextLine();
                        Cave loadedCave = Cave.loadGame(loadFileName);
                        if (loadedCave != null) {
                            cave = loadedCave;
                            System.out.println("Peli ladattu tiedostosta " + loadFileName + ". Tervetuloa takaisin, "
                                    + cave.player.getName() + ".");
                        }
                        break;
                    case 0:
                        System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Syöte oli väärä.");
                        break;

                }
            }
        }
        sc.close();
    }
}

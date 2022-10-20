import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Game {
    private static Player player = null;
    private static Battle battle = null;
    private static BufferedReader input;

    public static void main(String[] args) {
        input = new BufferedReader (new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Enter the player's name:");
        try {
            command(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Player(string, 200, 20, 20, 0, 0, 1);
            System.out.println("We welcome you valiant knight, " + player.getName());
            printNavigation();
        }
        switch (string) {
            case "1" -> {
                System.out.println("The merchant hasn't arrived yet");
                command(input.readLine());
            }
            case "2" -> {
                commitFight();
            }
            case "3" -> System.exit(1);
            case "yes" -> command("2");
            case "no" -> {
                printNavigation();
                command(input.readLine());
            }
        }
        command(input.readLine());
    }

    private static Character createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin("Goblin", 80, 15, 15, 140, 20, 2);
        else return new Skeleton("Skeleton", 25, 20, 20, 100, 10, 1);
    }

    private static void commitFight() {
        battle.fight(player,createMonster(),new FightCallBack() {
            @Override
            public void win() {
                System.out.println(String.format("%s won! Now you have %d experience and %d gold, as well as %d health units left.", player.getName(), player.getExperience(), player.getGold(), player.getHealth()));
                System.out.println("Do you want to continue hiking or return to the city? (yes/no)");
                try {
                    command(input.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void lose() {

            }
        });
    }

    private static void printNavigation() {
        System.out.println("Where do you want to go?");
        System.out.println("1. To the Merchant");
        System.out.println("2. Into the dark forest");
        System.out.println("3. Exit");
    }

    interface FightCallBack {
        void win();

        void lose();
    }
}



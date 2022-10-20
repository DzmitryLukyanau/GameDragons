public class Battle {
    public void fight(Character player, Character monster, Game.FightCallBack fightCallBack) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isEndBattle = false;
            while (!isEndBattle) {
                System.out.println("------ Turn:" + turn + " ------");
                if (turn++ % 2 != 0) {
                    isEndBattle = makeHit(monster, player, fightCallBack);
                } else {
                    isEndBattle = makeHit(player, monster, fightCallBack);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread tread = new Thread(runnable);
        tread.start();
    }

    private boolean makeHit(Character defender, Character attacker, Game.FightCallBack fightCallBack) {
        int hit = attacker.attack();
        int defenderHelth = defender.getHealth() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s dealt %d damage!", attacker.getName(), hit));
            System.out.println(String.format("The %s has %d health left", defender.getName(), defenderHelth));
        } else {
            System.out.println(String.format("%s missed!", attacker.getName()));
        }
        if (defenderHelth <= 0 && defender instanceof Player) {
            System.out.println("You have lost, the game is over!");
            System.exit(1);
            return true;
        } else if (defenderHelth <= 0) {
            System.out.println(String.format("You get %d experience and %d gold",defender.getExperience(),defender.getGold()));
            attacker.setExperience(attacker.getExperience()+defender.getExperience());
            attacker.setGold(attacker.getGold()+defender.getGold());
            attacker.checkLevelUp();
            fightCallBack.win();
            return true;
        }else {
            defender.setHealth(defenderHelth);
            return false;
        }
    }
}

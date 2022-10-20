public abstract class Character implements Fighter {
    private String name;
    private int health;
    private int dexterity;
    private int strength;
    private int experience;
    private int gold;
    private int level;

    private int experienceForLevelUp = 150;


    public Character(String name, int health, int dexterity, int strength, int experience, int gold, int level) {
        this.name = name;
        this.health = health;
        this.dexterity = dexterity;
        this.strength = strength;
        this.experience = experience;
        this.gold = gold;
        this.level = level;
    }

    @Override
    public int attack() {
        if (dexterity * 3 > getRandomNumber()) {
            if (criticalAttack()) return 2 * strength;
            else return strength;
        } else return 0;
    }

    public void checkLevelUp() {
        int n = experience / experienceForLevelUp;
        if (n > 0) {
            health += 10 * n;
            dexterity += 3 * n;
            strength += 3 * n;
            experience -= experienceForLevelUp * n;
            level += n;
            experienceForLevelUp *= 2;
            System.out.println(String.format("Congratulations, you got a new level %d. Your parameters have increased", level) );
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int getRandomNumber() {
        return (int) (Math.random() * 100);
    }

    private boolean criticalAttack() {
        return getRandomNumber() < (strength / 2);
    }

    @Override
    public String toString() {
        return String.format("%s, %d level, health: %d", name, level, health);
    }
}

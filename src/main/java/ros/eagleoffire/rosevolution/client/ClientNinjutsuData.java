package ros.eagleoffire.rosevolution.client;

public class ClientNinjutsuData {
    private static int experienceChakra;
    private static int levelChakra;
    private static int experienceHealth;
    private static int levelHealth;

    public static void set(int expC, int lvlC, int expH, int lvlH) {
        experienceChakra = expC;
        levelChakra = lvlC;
        experienceHealth = expH;
        levelHealth = lvlH;
    }

    public int getExperienceChakra(){
        return experienceChakra;
    }
    public int getLevelChakra(){
        return levelChakra;
    }
    public int getExperienceHealth() { return experienceHealth; }
    public int getLevelHealth(){
        return levelHealth;
    }
}

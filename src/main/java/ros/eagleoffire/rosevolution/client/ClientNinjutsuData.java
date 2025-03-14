package ros.eagleoffire.rosevolution.client;

public class ClientNinjutsuData {
    private static int experienceChakra;
    private static int levelChakra;
    private static int experienceHealth;
    private static int levelHealth;
    private static String clan;
    private static int tokenAmount;

    // Tracking daily experience caps
    private static int dailyExperienceChakraGained;
    private static int dailyExperienceHealthGained;
    private static long lastResetTimestamp;

    public static void set(int expC, int lvlC, int expH, int lvlH, String cln, int tkn, int dailyExpC, int dailyExpH, long lastReset) {
        experienceChakra = expC;
        levelChakra = lvlC;
        experienceHealth = expH;
        levelHealth = lvlH;
        clan = cln;
        tokenAmount = tkn;
        dailyExperienceChakraGained = dailyExpC;
        dailyExperienceHealthGained = dailyExpH;
        lastResetTimestamp = lastReset;
    }
}

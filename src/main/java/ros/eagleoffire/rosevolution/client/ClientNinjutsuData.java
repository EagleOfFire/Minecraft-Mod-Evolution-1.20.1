package ros.eagleoffire.rosevolution.client;

public class ClientNinjutsuData {
    private static int experience;
    private static int level;
    private static int chakra;

    public static void set(int exp, int lvl, int ch) {
        experience = exp;
        level = lvl;
        chakra = ch;
    }

    public static int getExperience() {
        return experience;
    }

    public static int getLevel() {
        return level;
    }

    public static int getChakra() {
        return chakra;
    }
}

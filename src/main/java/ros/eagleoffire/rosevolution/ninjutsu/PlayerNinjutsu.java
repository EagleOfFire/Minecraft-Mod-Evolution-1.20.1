package ros.eagleoffire.rosevolution.ninjutsu;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerNinjutsu {
    private int experienceChakra;
    private int levelChakra;
    private int experienceHealth;
    private int levelHealth;
    private String clan = "";

    // Tracking daily experience caps
    private int dailyExperienceChakraGained;
    private int dailyExperienceHealthGained;
    private long lastResetTimestamp;

    private static final long MILLISECONDS_IN_A_DAY = 86_400_000;

    // helper method to reset dauly experience if needed
    private void resetDailyExperienceIfNeeded() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResetTimestamp >= MILLISECONDS_IN_A_DAY) {
            dailyExperienceChakraGained = 0;
            dailyExperienceHealthGained = 0;
            lastResetTimestamp = currentTime;
        }
    }

    // Calculate XP limit forthegiven level
    private int getDailyXpLimit(int level) {
        if (level <= 190) {
            return 2 * PlayerLevelManager.getXpForNextLevel(level);
        } else if (level <= 416) {
            return (int) (1.5 * PlayerLevelManager.getXpForNextLevel(level));
        } else {
            return PlayerLevelManager.getXpForNextLevel(level);
        }
    }

    public void addExperienceChakra(int add){this.experienceChakra += add;}
    public void addExperienceHealth(int add){this.experienceHealth += add;}

    public int getExperienceChakra() {return experienceChakra;}
    public int getLevelChakra() {return levelChakra;}
    public int getExperienceHealth() {return experienceHealth;}
    public int getLevelHealth() {return levelHealth;}
    public String getClan() {return clan;}
    public int getDailyExperienceChakraGained() {return dailyExperienceChakraGained;}
    public int getDailyExperienceHealthGained() {return dailyExperienceHealthGained;}
    public long getLastResetTimestamp() {return lastResetTimestamp;}

    public void setLevelChakra(int lvl) {this.levelChakra = lvl;}
    public void setLevelHealth(int lvl) {this.levelHealth = lvl;}
    public void setClan(String clan) {this.clan = clan;}

    public void copyFrom(PlayerNinjutsu source) {
        this.experienceChakra = source.experienceChakra;
        this.levelChakra = source.levelChakra;
        this.experienceHealth = source.experienceHealth;
        this.levelHealth = source.levelHealth;
        this.dailyExperienceChakraGained = source.dailyExperienceChakraGained;
        this.dailyExperienceHealthGained = source.dailyExperienceHealthGained;
        this.lastResetTimestamp = source.lastResetTimestamp;
        this.clan = source.clan;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("experienceChakra", experienceChakra);
        nbt.putInt("levelChakra", levelChakra);
        nbt.putInt("experienceHealth", experienceHealth);
        nbt.putInt("levelHealth", levelHealth);
        nbt.putInt("dailyExperienceChakraGained", dailyExperienceChakraGained);
        nbt.putInt("dailyExperienceHealthGained", dailyExperienceHealthGained);
        nbt.putLong("lastResetTimestamp", lastResetTimestamp);
        nbt.putString("clan", clan);
        System.out.println("Saved PlayerNinjutsu to NBT: " + nbt);
    }

    public void loadNBTData(CompoundTag nbt) {
        experienceChakra = nbt.getInt("experienceChakra");
        levelChakra = nbt.getInt("levelChakra");
        experienceHealth = nbt.getInt("experienceHealth");
        levelHealth = nbt.getInt("levelHealth");
        dailyExperienceChakraGained = nbt.getInt("dailyExperienceChakraGained");
        dailyExperienceHealthGained = nbt.getInt("dailyExperienceChakraGained");
        lastResetTimestamp = nbt.getLong("lastResetTimestamp");
        clan = nbt.getString("clan");
        System.out.println("Loaded PlayerNinjutsu from NBT: " + nbt);
    }

    public void reset() {
        experienceChakra = 0;
        experienceHealth = 0;
        dailyExperienceChakraGained = 0;
        dailyExperienceHealthGained = 0;
        lastResetTimestamp = System.currentTimeMillis();
        clan = "";
    }
}

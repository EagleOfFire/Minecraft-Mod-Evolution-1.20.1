package ros.eagleoffire.rosevolution.ninjutsu;

import net.minecraft.nbt.CompoundTag;

public class PlayerNinjutsu {
    private int experienceChakra;
    private int levelChakra;
    private int experienceHealth;
    private int levelHealth;

    public int getExperienceChakra(){
        return experienceChakra;
    }
    public int getLevelChakra(){
        return levelChakra;
    }
    public void addExperienceChakra(int add){
        this.experienceChakra = experienceChakra+add;
    }
    public void setLevelChakra(int lvl){
        this.levelChakra = lvl;
    }

    public int getExperienceHealth() { return experienceHealth; }
    public int getLevelHealth(){
        return levelHealth;
    }
    public void addExperienceHealth(int add){
           this.experienceHealth = experienceHealth+add;
    }
    public void setLevelHealth(int lvl){
        this.levelHealth = lvl;
    }


    public void copyFrom(PlayerNinjutsu source){
        this.experienceChakra = source.experienceChakra;
        this.levelChakra = source.levelChakra;
        this.experienceHealth = source.experienceHealth;
        this.levelHealth = source.levelHealth;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("experienceChakra", experienceChakra);
        nbt.putInt("levelChakra", levelChakra);
        nbt.putInt("experienceHealth", experienceHealth);
        nbt.putInt("levelHealth", levelHealth);
    }

    public void loadNBTData(CompoundTag nbt){
        experienceChakra = nbt.getInt("experienceChakra");
        levelChakra = nbt.getInt("levelChakra");
        experienceHealth = nbt.getInt("experienceHealth");
        levelHealth = nbt.getInt("levelHealth");
    }
}

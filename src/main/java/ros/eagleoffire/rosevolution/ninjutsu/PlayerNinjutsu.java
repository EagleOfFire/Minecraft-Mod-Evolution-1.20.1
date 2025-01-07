package ros.eagleoffire.rosevolution.ninjutsu;

import net.minecraft.nbt.CompoundTag;

public class PlayerNinjutsu {
    private int experience;
    private int level;
    private final int MIN_EXP = 0;
    private final int MAX_EXP = 100;

    public int getExperience(){
        return experience;
    }

    public int getLevel(){
        return level;
    }

    public void addExperience(int add){
           this.experience = Math.min(experience+add,MAX_EXP);
    }

    public void copyFrom(PlayerNinjutsu source){
        this.experience = source.experience;
        this.level = source.level;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("experience", experience);
        nbt.putInt("level", level);
    }

    public void loadNBTData(CompoundTag nbt){
        experience = nbt.getInt("experience");
        level = nbt.getInt("level");
    }
}

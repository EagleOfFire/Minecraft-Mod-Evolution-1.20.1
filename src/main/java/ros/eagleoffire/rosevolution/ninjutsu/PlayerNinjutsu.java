package ros.eagleoffire.rosevolution.ninjutsu;

import net.minecraft.nbt.CompoundTag;

public class PlayerNinjutsu {
    private int experience;
    private int level;
    private int chakra;
    private final int MIN_EXP = 0;
    private final int MAX_EXP = 100;

    public int getExperience(){
        return experience;
    }

    public int getLevel(){
        return level;
    }

    public int getMaxChakra() { return chakra; }

    public void addExperience(int add){
           this.experience = Math.min(experience+add,MAX_EXP);
    }

    public void copyFrom(PlayerNinjutsu source){
        this.experience = source.experience;
        this.level = source.level;
        this.chakra = source.chakra;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("experience", experience);
        nbt.putInt("level", level);
        nbt.putInt("chakra", chakra);
    }

    public void loadNBTData(CompoundTag nbt){
        experience = nbt.getInt("experience");
        level = nbt.getInt("level");
        chakra = nbt.getInt("chakra");
    }
}

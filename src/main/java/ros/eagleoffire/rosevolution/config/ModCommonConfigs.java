package ros.eagleoffire.rosevolution.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for ROS Evolution Mod");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

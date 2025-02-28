package ros.eagleoffire.rosevolution.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;
import ros.eagleoffire.rosevolution.ROSEvolution;

import java.io.File;
import java.nio.file.Path;

public class ModCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for ROS Evolution Mod");

        BUILDER.pop();
        SPEC = BUILDER.build();

        // Get the config folder path for the mod
        Path configPath = FMLPaths.CONFIGDIR.get().resolve(ROSEvolution.MODID);
        File modConfigDir = configPath.toFile();
        File texturesFolder = new File(modConfigDir, "textures");
        texturesFolder.mkdirs();
        File spellFolder = new File(modConfigDir, "spell");
        spellFolder.mkdirs();
    }
}

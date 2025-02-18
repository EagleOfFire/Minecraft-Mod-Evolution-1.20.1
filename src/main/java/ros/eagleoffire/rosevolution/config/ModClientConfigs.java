package ros.eagleoffire.rosevolution.config;

import net.minecraftforge.common.ForgeConfigSpec;
import ros.eagleoffire.rosevolution.ROSEvolution;

import java.util.List;

public class ModClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<String>> BUTTON_TEXTS;
    public static final ForgeConfigSpec.ConfigValue<List<String>> BUTTON_IMAGES;
    public static final ForgeConfigSpec.ConfigValue<List<String>> BUTTON_COMMANDS;

    static {
        BUILDER.push("Configs for ROS Evolution Mod");

        BUTTON_TEXTS = BUILDER.comment("Texts labels for buttons in the dynamis menu.")
                        .define("Button texts", List.of("Button 1", "Button 2"));

        BUTTON_IMAGES = BUILDER.comment("Image paths for buttons in the dynamic menu.")
                        .define("Button Images", List.of(ROSEvolution.MODID + "/textures/button1.png", ROSEvolution.MODID + "textures/button2.png"));

        BUTTON_COMMANDS = BUILDER.comment("Commandes taht each button will execute when clicked.")
                        .define("Button Commands", List.of("/command1", "/command2"));
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

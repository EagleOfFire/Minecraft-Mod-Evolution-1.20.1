package ros.eagleoffire.rosevolution.config;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<String>> BUTTON_TEXTS;
    private static final ForgeConfigSpec.ConfigValue<List<String>> BUTTON_IMAGES_RAW; // Store as raw strings
    public static final ForgeConfigSpec.ConfigValue<List<String>> BUTTON_COMMANDS;

    static {
        BUILDER.push("Configs for ROS Evolution Mod");

        BUTTON_TEXTS = BUILDER.comment("Text labels for buttons in the dynamic menu.")
                .define("Button Texts", List.of("Button 1", "Button 2"));

        BUTTON_IMAGES_RAW = BUILDER.comment("Image paths for buttons in the dynamic menu.")
                .define("Button Images", List.of("button.png", "button.png"));

        BUTTON_COMMANDS = BUILDER.comment("Commands that each button will execute when clicked.")
                .define("Button Commands", List.of("gamemode survival @s", "gamemode creative @s"));

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    /**
     * Converts stored string paths into a list of ResourceLocation objects.
     */
    public static Map<String, DynamicTexture> getButtonImages() {
        Map<String, DynamicTexture> textures = new HashMap<>();

        BUTTON_IMAGES_RAW.get().forEach(path -> {
            String cleanPath = path.contains(":") ? path.split(":")[1] : path;
            Path configPath = FMLPaths.CONFIGDIR.get().resolve("rosevolution/textures/" + cleanPath);
            File textureFile = configPath.toFile();

            if (textureFile.exists()) {
                try {
                    NativeImage nativeImage = NativeImage.read(new FileInputStream(textureFile));
                    DynamicTexture dynamicTexture = new DynamicTexture(nativeImage);
                    textures.put(cleanPath, dynamicTexture);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return textures;
    }
}

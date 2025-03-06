package ros.eagleoffire.rosevolution.config;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<String>> SPELLS_NAMES;
    private static final ForgeConfigSpec.ConfigValue<List<String>> SPELLS_ICON_RAW; // Store as raw strings
    public static final ForgeConfigSpec.ConfigValue<List<String>> SPELLS_COMMAND;
    public static final ForgeConfigSpec.ConfigValue<List<String>> SPELLS_RANKS;
    public static final ForgeConfigSpec.ConfigValue<List<String>> SPELLS_CATEGORIES;

    static {
        BUILDER.push("Configs for ROS Evolution Mod");

        SPELLS_NAMES = BUILDER.comment("Libellés des boutons dans le menu dynamique.")
                .define("Textes des boutons", List.of("Bouton 1", "Bouton 2"));

        SPELLS_ICON_RAW = BUILDER.comment("Chemins des images pour les boutons du menu dynamique.")
                .define("Images des boutons", List.of("button.png", "button.png"));

        SPELLS_COMMAND = BUILDER.comment("Commandes exécutées par chaque bouton lorsqu'il est cliqué.")
                .define("Commandes des boutons", List.of("gamemode survival @s", "gamemode creative @s"));

        SPELLS_RANKS = BUILDER.comment("Niveaux ou rangs associés aux boutons du menu dynamique.")
                .define("Rang du spell", List.of("A", "B"));

        SPELLS_CATEGORIES = BUILDER.comment("Catégories associées aux spell du menu dynamique.")
                .define("Catégories du spell", List.of("Element", "Genjutsu"));

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    /**
     * Converts stored string paths into a list of ResourceLocation objects.
     */
    public static Map<String, DynamicTexture> getButtonImages() {
        Map<String, DynamicTexture> textures = new HashMap<>();

        SPELLS_ICON_RAW.get().forEach(path -> {
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

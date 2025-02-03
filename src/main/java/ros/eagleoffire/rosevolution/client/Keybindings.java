package ros.eagleoffire.rosevolution.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import ros.eagleoffire.rosevolution.ROSEvolution;

public final class Keybindings {
    public static final Keybindings INSTANCE = new Keybindings();

    private Keybindings(){}

    private static final String CATEGORY = "key.categories." + ROSEvolution.MODID;

    public final KeyMapping OpenSpellScreen = new KeyMapping(
            "key." + ROSEvolution.MODID + ".open_spell_screen",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_P, -1),
            CATEGORY
    );
}

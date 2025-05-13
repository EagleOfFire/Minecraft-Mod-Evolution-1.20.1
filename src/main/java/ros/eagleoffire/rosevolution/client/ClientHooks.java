package ros.eagleoffire.rosevolution.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import ros.eagleoffire.rosevolution.screen.*;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.*;

public class ClientHooks {
    public static void openLevelUpScreen(){
        Minecraft.getInstance().setScreen(new LevelUpScreen());
    }

    public static void openSpecialisationScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new SpecialisationScreen(player));
    }

    public static void openElementSelectScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new ElementSelectScreen(player));
    }

    public static void openSpellSelectScreen(String element, String rank) {
        Minecraft.getInstance().setScreen(new SpellsScreens(rank,element));
    }
}

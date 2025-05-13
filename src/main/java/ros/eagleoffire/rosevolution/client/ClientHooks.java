package ros.eagleoffire.rosevolution.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import ros.eagleoffire.rosevolution.screen.*;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.*;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.elementScreen.*;

public class ClientHooks {
    public static void openLevelUpScreen(){
        Minecraft.getInstance().setScreen(new LevelUpScreen());
    }

    public static void openSpellSelectScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new SpellSelectScreen(player));
    }

    public static void openKatonJutsuScreen(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new KatonJutsuScreen(player));
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

package ros.eagleoffire.rosevolution.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import ros.eagleoffire.rosevolution.screen.KatonJutsuScreen;
import ros.eagleoffire.rosevolution.screen.SpecialisationScreen;
import ros.eagleoffire.rosevolution.screen.SpellSelectScreen;
import ros.eagleoffire.rosevolution.screen.TestScreen;

public class ClientHooks {
    public static void openSpellSelectScreen(LocalPlayer player){
        Minecraft.getInstance().setScreen(new SpellSelectScreen(player));
    }

    public static void openKatonJutsuScreen(LocalPlayer player){
        Minecraft.getInstance().setScreen(new KatonJutsuScreen(player));
    }

    public static void openTestScreen(LocalPlayer player){
        Minecraft.getInstance().setScreen(new TestScreen(player));
    }

    public static void openSpecialisationScreen(LocalPlayer player){
        Minecraft.getInstance().setScreen(new SpecialisationScreen(player));
    }
}

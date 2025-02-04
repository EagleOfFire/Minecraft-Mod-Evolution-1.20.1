package ros.eagleoffire.rosevolution.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import ros.eagleoffire.rosevolution.screen.SpellSelectScreen;

public class ClientHooks {
    public static void openSpellSelectScreen(LocalPlayer player){
        Minecraft.getInstance().setScreen(new SpellSelectScreen(player));
    }
}

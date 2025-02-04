package ros.eagleoffire.rosevolution.handler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;
import ros.eagleoffire.rosevolution.client.Keybindings;
import net.minecraft.network.chat.Component;
import ros.eagleoffire.rosevolution.screen.SpellSelectScreen;

@Mod.EventBusSubscriber(modid = ROSEvolution.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class ClientForgeHandler {
    private static final Component OPEN_SPELL_SCREEN =
            Component.translatable("message." + ROSEvolution.MODID + ".open_spell_screen_pressed");

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event){
        Minecraft minecraft = Minecraft.getInstance();
        if (Keybindings.INSTANCE.OpenSpellScreen.consumeClick() && minecraft.player != null) {
            minecraft.player.displayClientMessage(OPEN_SPELL_SCREEN, true);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openSpellSelectScreen(minecraft.player));
        }
    }
}

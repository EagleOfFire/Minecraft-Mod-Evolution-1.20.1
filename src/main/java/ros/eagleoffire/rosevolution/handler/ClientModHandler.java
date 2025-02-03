package ros.eagleoffire.rosevolution.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.Keybindings;

@Mod.EventBusSubscriber(modid = ROSEvolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientModHandler {
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event){
        event.register(Keybindings.INSTANCE.OpenSpellScreen);
    }
}

package ros.eagleoffire.rosevolution.events;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.network.PacketHandler;

@Mod.EventBusSubscriber(modid = ROSEvolution.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() ->{
            PacketHandler.register();
        });
    }
}

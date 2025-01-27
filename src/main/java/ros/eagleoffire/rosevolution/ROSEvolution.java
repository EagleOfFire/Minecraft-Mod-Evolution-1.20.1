package ros.eagleoffire.rosevolution;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ros.eagleoffire.rosevolution.network.ModMessages;
import ros.eagleoffire.rosevolution.quest.ActivityQuestTracker;
import ros.eagleoffire.rosevolution.sound.ModSounds;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ROSEvolution.MODID)
public class ROSEvolution
{
    public static final String MODID = "rosevolution";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ROSEvolution()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ActivityQuestTracker.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }
}

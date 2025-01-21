package ros.eagleoffire.rosevolution.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import ros.eagleoffire.rosevolution.commands.GetNinjutsuLevelCommand;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.network.PacketHandler;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsu;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;
import net.minecraftforge.server.command.ConfigCommand;


@Mod.EventBusSubscriber(modid = ROSEvolution.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new GetNinjutsuLevelCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).isPresent()) {
                event.addCapability(new ResourceLocation(ROSEvolution.MODID, "properties"), new PlayerNinjutsuProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerNinjutsu.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            });
        }
    }
}

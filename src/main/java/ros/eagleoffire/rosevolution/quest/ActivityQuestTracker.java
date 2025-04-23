package ros.eagleoffire.rosevolution.quest;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ros.eagleoffire.rosevolution.Utils.AFKTracker;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerLevelManager;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class ActivityQuestTracker {
    private static final Map<UUID, PlayerQuestData> playerData = new HashMap<>();
    private static final long TEN_MINUTES_TICKS = 20 * 60 * 10; // 10 minutes in ticks (20 ticks/sec)

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.level().isClientSide || !(event.player instanceof ServerPlayer)) {
            return;
        }
        ServerPlayer player = (ServerPlayer) event.player;
        UUID playerId = player.getUUID();

        // Initialize or update player data
        PlayerQuestData data = playerData.computeIfAbsent(playerId, k -> new PlayerQuestData());
        if (AFKTracker.isPlayerAFK(player)) {
            data.resetActiveTime(); // Reset if AFK
        } else {
            data.incrementActiveTime();
        }

        // Check if the player has been active for 10 minutes
        if (data.getActiveTicks() >= TEN_MINUTES_TICKS) {
            data.resetActiveTime(); // Reset timer
            grantRewards(player);  // Reward the player
        }
    }

    private static void grantRewards(ServerPlayer player) {
        player.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            ninjutsu.addExperienceChakra(3);
            ninjutsu.addExperienceHealth(3);
            PlayerLevelManager.checkLevelUps(player,ninjutsu);
        });
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        playerData.remove(event.getEntity().getUUID()); // Clean up data on logout
    }

    @SubscribeEvent
    public static void onServerStop(ServerStoppingEvent event) {
        playerData.clear(); // Clear data when the server stops
    }
}

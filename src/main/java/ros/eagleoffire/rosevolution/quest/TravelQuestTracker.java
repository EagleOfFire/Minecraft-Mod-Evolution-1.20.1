package ros.eagleoffire.rosevolution.quest;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerLevelManager;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class TravelQuestTracker {
    private static final Map<UUID, PlayerQuestData> playerData = new HashMap<>();
    private static final long TEN_MINUTES_TICKS = 20 * 60 * 10; // 10 minutes in ticks (20 ticks/sec)

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Ensure this is only executed at the END phase
            Player player = event.player;
            if (player instanceof ServerPlayer serverPlayer) {
                UUID playerId = player.getUUID();
                PlayerQuestData data = playerData.computeIfAbsent(playerId, k -> new PlayerQuestData());

                // Calculate distance traveled
                double distanceTraveled = data.calculateDistance(player.getX(), player.getY(), player.getZ());
                data.addDistanceTraveled(distanceTraveled);

                // Check if distance traveled meets quest goal
                if (data.getDistanceTraveled() >= 5000 && !data.isTravelOnCooldown()) {
                    grantRewards(serverPlayer);
                    data.resetDistanceTraveled();
                    data.setTravelCooldown(System.currentTimeMillis());
                }
            }
        }
    }

    private static void grantRewards(ServerPlayer player) {
        player.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            ninjutsu.addExperienceHealth(5);
            PlayerLevelManager.checkLevelUps(player, ninjutsu);
            player.sendSystemMessage(Component.literal("You have gained 3 Health XP!"));
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

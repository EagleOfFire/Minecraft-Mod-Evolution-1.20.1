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
                if (data.getDistanceTraveled() >= 5000 && !data.isOnCooldown()) {
                    grantRewards(serverPlayer);
                    data.resetDistanceTraveled();
                    data.setCooldown(System.currentTimeMillis());
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

    // Inner class for tracking player quest data
    private static class PlayerQuestData {
        private double totalDistanceTraveled = 0;
        private double lastX = Double.NaN, lastY = Double.NaN, lastZ = Double.NaN;
        private long cooldownEndTime = 0;

        public double calculateDistance(double currentX, double currentY, double currentZ) {
            if (Double.isNaN(lastX)) { // Initialize position tracking
                lastX = currentX;
                lastY = currentY;
                lastZ = currentZ;
                return 0;
            }
            double distance = Math.sqrt((currentX - lastX) * (currentX - lastX)
                    + (currentY - lastY) * (currentY - lastY)
                    + (currentZ - lastZ) * (currentZ - lastZ));
            lastX = currentX;
            lastY = currentY;
            lastZ = currentZ;
            return distance;
        }

        public void addDistanceTraveled(double distance) {
            totalDistanceTraveled += distance;
        }

        public double getDistanceTraveled() {
            return totalDistanceTraveled;
        }

        public void resetDistanceTraveled() {
            totalDistanceTraveled = 0;
        }

        public boolean isOnCooldown() {
            return System.currentTimeMillis() < cooldownEndTime;
        }

        public void setCooldown(long currentTimeMillis) {
            cooldownEndTime = currentTimeMillis + 30 * 60 * 1000; // 30 minutes cooldown
        }
    }
}

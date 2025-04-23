package ros.eagleoffire.rosevolution.ninjutsu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import ros.eagleoffire.rosevolution.network.ModMessages;
import ros.eagleoffire.rosevolution.network.packets.NinjutsuDataSyncS2CPacket;
import ros.eagleoffire.rosevolution.network.packets.OpenLevelUpScreenS2CPacket;

import java.util.UUID;

public class PlayerLevelManager {
    private static final UUID HEALTH_BONUS_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    public static void checkLevelUps(Player player, PlayerNinjutsu cap) {
        checkLevelUp(player, cap, cap.getExperienceChakra(), cap.getLevelChakra(), true);
        checkLevelUp(player, cap, cap.getExperienceHealth(), cap.getLevelHealth(), false);
    }

    private static void checkLevelUp(Player player, PlayerNinjutsu cap, int experience, int currentLevel, boolean isChakra) {
        int newLevel = currentLevel;
        int requiredXp = getXpForNextLevel(currentLevel + 1); // Next level threshold

        while (experience >= requiredXp) {
            newLevel++;
            requiredXp = getXpForNextLevel(newLevel + 1); // Calculate next level threshold
        }

        if (newLevel != currentLevel) {
            if (isChakra) {
                cap.setLevelChakra(newLevel);
            } else {
                cap.setLevelHealth(newLevel);
            }

            applyStatUpgrades(player,cap);
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(cap), (ServerPlayer) player);
            ModMessages.sendToPlayer(new OpenLevelUpScreenS2CPacket(), (ServerPlayer) player);
        }
    }

    public static int getXpForNextLevel(int level) {
        if (level <= 190) return level * 15;
        if (level <= 370) return 190 * 15 + (level - 190) * 20;
        return 190 * 15 + 180 * 20 + (level - 370) * 30;
    }

    public static void applyStatUpgrades(Player player, PlayerNinjutsu cap) {
        AttributeInstance healthAttribute = player.getAttribute(Attributes.MAX_HEALTH);
        if (healthAttribute != null) {
            healthAttribute.removeModifier(HEALTH_BONUS_UUID);
            double bonusHealth = 10 + ((cap.getLevelHealth() - 1) * 1.0);
            AttributeModifier healthModifier = new AttributeModifier(HEALTH_BONUS_UUID, "Health Bonus", bonusHealth, AttributeModifier.Operation.ADDITION);
            healthAttribute.addPermanentModifier(healthModifier);
        }
    }
}


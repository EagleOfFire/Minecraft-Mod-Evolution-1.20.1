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

import java.awt.*;
import java.util.UUID;

public class PlayerLevelManager {
    private static final UUID HEALTH_BONUS_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    public static void checkLevelUps(Player player, PlayerNinjutsu cap) {
        // Check Chakra level-up
        int requiredChakraXp = getXpForNextLevel(cap.getLevelChakra());
        if (cap.getExperienceChakra() >= requiredChakraXp) {
            cap.setLevelChakra(cap.getLevelChakra() + 1);
            cap.addExperienceChakra(-requiredChakraXp);
            applyStatUpgrades(player, cap.getLevelChakra());
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(cap), (ServerPlayer) player);
            ModMessages.sendToPlayer(new OpenLevelUpScreenS2CPacket(), (ServerPlayer) player);
            player.sendSystemMessage(Component.literal("Level Up! Chakra increased to level " + cap.getLevelChakra() + "!"));
        }

        // Check Health level-up
        int requiredHealthXp = getXpForNextLevel(cap.getLevelHealth());
        if (cap.getExperienceHealth() >= requiredHealthXp) {
            cap.setLevelHealth(cap.getLevelHealth() + 1);
            cap.addExperienceHealth(-requiredHealthXp);
            applyStatUpgrades(player, cap.getLevelHealth());
            ModMessages.sendToPlayer(new NinjutsuDataSyncS2CPacket(cap), (ServerPlayer) player);
            ModMessages.sendToPlayer(new OpenLevelUpScreenS2CPacket(), (ServerPlayer) player);
            player.sendSystemMessage(Component.literal("Level Up! Health increased to level " + cap.getLevelHealth() + "!"));
        }
    }

    public static int getXpForNextLevel(int level) {
        return (int) (0.8378* Math.pow(1.01,level));
    }

    private static void applyStatUpgrades(Player player, int level) {
        AttributeInstance healthAttribute = player.getAttribute(Attributes.MAX_HEALTH);

        if (healthAttribute != null) {
            // Remove the existing modifier if it exists
            healthAttribute.removeModifier(HEALTH_BONUS_UUID);

            // Calculate bonus health (e.g., +2 health per level)
            double bonusHealth = 10 + ((level - 1) * 1.0);

            // Create and add the new modifier
            AttributeModifier healthModifier = new AttributeModifier(
                HEALTH_BONUS_UUID,
                "Level-Up Bonus Health",
                bonusHealth,
                AttributeModifier.Operation.ADDITION
            );

            healthAttribute.addPermanentModifier(healthModifier);
            //TODO add chakra modifier
        }
    }
}


package ros.eagleoffire.rosevolution.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.network.packets.AddChakraC2SPacket;
import ros.eagleoffire.rosevolution.network.packets.NinjutsuDataSyncS2CPacket;
import ros.eagleoffire.rosevolution.network.packets.OpenLevelUpScreenS2CPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ROSEvolution.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(NinjutsuDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(NinjutsuDataSyncS2CPacket::new)
                .encoder(NinjutsuDataSyncS2CPacket::toBytes)
                .consumerMainThread(NinjutsuDataSyncS2CPacket::handle)
                .add();

        net.messageBuilder(AddChakraC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AddChakraC2SPacket::new)
                .encoder(AddChakraC2SPacket::toBytes)
                .consumerMainThread(AddChakraC2SPacket::handle)
                .add();

        net.messageBuilder(OpenLevelUpScreenS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(OpenLevelUpScreenS2CPacket::new)
                .encoder(OpenLevelUpScreenS2CPacket::toBytes)
                .consumerMainThread(OpenLevelUpScreenS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
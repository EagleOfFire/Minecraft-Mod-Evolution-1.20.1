package ros.eagleoffire.rosevolution.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.network.packets.AddChakraC2SPacket;

public class PacketHandler {
    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(ROSEvolution.MODID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register(){
        INSTANCE.messageBuilder(AddChakraC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AddChakraC2SPacket::new)
                .encoder(AddChakraC2SPacket::toBytes)
                .consumerMainThread(AddChakraC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(),message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}

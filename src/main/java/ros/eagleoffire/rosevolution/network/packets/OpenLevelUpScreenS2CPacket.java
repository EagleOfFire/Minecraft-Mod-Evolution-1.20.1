package ros.eagleoffire.rosevolution.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import ros.eagleoffire.rosevolution.client.ClientHooks;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsu;
import ros.eagleoffire.rosevolution.screen.LevelUpScreen;

import java.util.function.Supplier;

public class OpenLevelUpScreenS2CPacket {

    public OpenLevelUpScreenS2CPacket() {}

    public OpenLevelUpScreenS2CPacket(FriendlyByteBuf friendlyByteBuf) {
    }

    public void toBytes(FriendlyByteBuf buf) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientHooks::openLevelUpScreen);
        });
        return true;
    }
}


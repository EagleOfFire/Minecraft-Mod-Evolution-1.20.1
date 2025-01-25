package ros.eagleoffire.rosevolution.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
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
            Minecraft.getInstance().setScreen(new LevelUpScreen());
        });
        return true;
    }
}


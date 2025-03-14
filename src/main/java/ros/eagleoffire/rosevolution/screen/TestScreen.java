package ros.eagleoffire.rosevolution.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;

public class TestScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

    private static final ResourceLocation S_RANK =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/rank/s_rank.png");
    private static final ResourceLocation A_RANK =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/rank/a_rank.png");
    private static final ResourceLocation B_RANK =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/rank/b_rank.png");
    private static final ResourceLocation C_RANK =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/rank/c_rank.png");
    private static final ResourceLocation D_RANK =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/rank/d_rank.png");
    private final LocalPlayer player;
    private final int imageWidth;
    private final int imageHeight;
    private int leftPos;
    private int topPos;

    public TestScreen(LocalPlayer player) {
        super(TITLE);
        this.player = player;
        this.imageWidth = 431;
        this.imageHeight = 532;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);

        int textureWidth = this.width / 5; // 1/5 of the screen width
        graphics.blit(S_RANK, 0, (int) (this.height / 4.0),0, 0, textureWidth, (int)(textureWidth*1.257),textureWidth, (int)(textureWidth*1.257));
        graphics.blit(A_RANK, textureWidth, (int) (this.height / 4.0),0, 0, textureWidth, (int)(textureWidth*1.257),textureWidth, (int)(textureWidth*1.257));
        graphics.blit(B_RANK, textureWidth*2, (int) (this.height / 4.0),0, 0, textureWidth, (int)(textureWidth*1.257),textureWidth, (int)(textureWidth*1.257));
        graphics.blit(C_RANK, textureWidth*3, (int) (this.height / 4.0),0, 0, textureWidth, (int)(textureWidth*1.257),textureWidth, (int)(textureWidth*1.257));
        graphics.blit(D_RANK, textureWidth*4, (int) (this.height / 4.0),0, 0, textureWidth, (int)(textureWidth*1.257),textureWidth, (int)(textureWidth*1.257));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
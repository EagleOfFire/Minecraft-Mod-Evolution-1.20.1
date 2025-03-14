package ros.eagleoffire.rosevolution.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;
import ros.eagleoffire.rosevolution.ninjutsu.PlayerNinjutsuProvider;

public class KatonJutsuScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".katon_jutsu_screen");
    private static final Component EXAMPLE_BUTTON =
            Component.translatable("gui." + ROSEvolution.MODID + ".katon_jutsu_screen.button.example_button");

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/spell_select_screen.png");
    private static final ResourceLocation TOKEN =
            new ResourceLocation(ROSEvolution.MODID, "textures/gui/token.png");

    private final int imageWidth, imageHeight;
    private final LocalPlayer player;

    private int leftPos, topPos;

    private Button button;
    private int tokenCount;

    public KatonJutsuScreen(LocalPlayer player) {
        super(TITLE);

        this.player = player;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        // Retrieve token count from capability
        player.getCapability(PlayerNinjutsuProvider.PLAYER_NINJUTSU).ifPresent(ninjutsu -> {
            this.tokenCount = ninjutsu.getTokenAmount(); // Assuming getTokens() exists in PlayerNinjutsu
        });

        this.button = addRenderableWidget(
                Button.builder(
                                EXAMPLE_BUTTON,
                                this::handleExampleButton)
                        .bounds(this.leftPos + 8, this.topPos + 20, 80, 20)
                        .tooltip(Tooltip.create(EXAMPLE_BUTTON))
                        .build());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font,
                TITLE,
                this.leftPos + 8,
                this.topPos + 8,
                0x404040,
                false);

        // Token text position
        int textX = this.leftPos + 8;
        int textY = this.topPos + 44;

        graphics.drawString(this.font,
                "Token restant: " + tokenCount, // Display stored token count
                textX,
                textY,
                0xFF0000,
                false);

        // Draw TOKEN icon next to the text
        int iconSize = 16; // Adjust size if needed
        int iconX = textX + this.font.width("Token restant: " + tokenCount) + 4; // Position it next to the text
        int iconY = textY - 4; // Align vertically

        graphics.blit(TOKEN, iconX, iconY, 0, 0, iconSize, iconSize,iconSize,iconSize);
        //graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

private void handleExampleButton(Button button) {
    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
        if (minecraft != null && minecraft.player != null) {
            ClientHooks.openTestScreen(minecraft.player);
        }
    });
}


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
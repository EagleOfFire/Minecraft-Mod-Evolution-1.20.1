package ros.eagleoffire.rosevolution.screen.specialisationScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;

import static ros.eagleoffire.rosevolution.screen.specialisationScreen.TexturesScreen.*;

public class ElementSelectScreen extends Screen {

     private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

    private static int backButtonLeftPos;
    private static int backButtonTopPos;
    private static int backButtonHeight;
    private static int backButtonWidth;
    private int leftPos;
    private int topPos;
    private LocalPlayer player;
    private static Button buttonBack;

    public ElementSelectScreen(LocalPlayer player) {
        super(TITLE);
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
               backButtonWidth = 40;
        backButtonHeight = (int) (0.625 * backButtonWidth);
        backButtonLeftPos = backButtonWidth / 2;
        backButtonTopPos = height - backButtonHeight - (backButtonWidth / 2);

        buttonBack = addRenderableWidget(new ImageButton(
                backButtonLeftPos, backButtonTopPos, // X and Y position
                backButtonWidth, backButtonHeight, // Width and Height
                0, 0, // Texture X and Y (start of image)
                0, // Amount to shift Y on click
                TexturesScreen.BACK_ARROW, // Texture location
                backButtonWidth, backButtonHeight, // Total texture size (width, height of full image)
                (button) -> {
                    if (minecraft != null && minecraft.player != null) {
                        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openSpecialisationScreen(player));
                    }
                }));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderBackground(graphics);
        int posCursor = getSectorID(mouseX,mouseY, this.width,this.height);
        if (posCursor == 1) {
            graphics.blit(ELEMENT_KATON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 2) {
            graphics.blit(ELEMENT_SUITON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 3) {
            graphics.blit(ELEMENT_FUTON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 4) {
            graphics.blit(ELEMENT_DOTON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else if (posCursor == 5) {
            graphics.blit(ELEMENT_RAITON, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        } else {
            graphics.blit(ELEMENT_BACKGROUND, this.leftPos, this.topPos, 0, 0, this.width, this.height,this.width,this.height);
        }

        if (buttonBack.isHoveredOrFocused()) {
            graphics.pose().pushPose(); // Save previous render state
            graphics.pose().translate(0, 0, 500); // Move it to the top layer
            graphics.blit(TexturesScreen.BACK_ARROW_HOVER,
                    backButtonLeftPos, backButtonTopPos,
                    0, 0,
                    backButtonWidth, backButtonHeight,
                    backButtonWidth, backButtonHeight);
            graphics.pose().popPose(); // Restore previous state
        }

        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int pButton) {
        int posCursor = getSectorID(mouseX,mouseY, this.width,this.height);
        if (posCursor == 1) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Katon", "D"));
        } else if (posCursor == 2) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Suiton", "D"));
        } else if (posCursor == 3) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Futon", "D"));
        } else if (posCursor == 4) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Doton", "D"));
        } else if (posCursor == 5) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, "Raiton", "D"));
        }
        return super.mouseClicked(mouseX, mouseY, pButton);
    }

    public static int getSectorID(double mouseX,double mouseY,int width, int height) {
        if (mouseY < ((double) height / 2) || mouseY > ((double) height / 4)*3) {
            return 0;
        }
        if (mouseX < ((double) width / 4)) {
            return 1;
        } else if (((double) width / 4) < mouseX && mouseX < ((double) width / 5)*2) {
            return 2;
        } else if (((double) width / 5)*2 < mouseX && mouseX < ((double) width / 5)*3) {
            return 3;
        } else if (((double) width / 5)*3 < mouseX && mouseX < ((double) width / 4)*3) {
            return 4;
        } else if (((double) width / 5)*4 < mouseX && mouseX < ((double) width / 4)*5) {
            return 5;
        }
        return 0;
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

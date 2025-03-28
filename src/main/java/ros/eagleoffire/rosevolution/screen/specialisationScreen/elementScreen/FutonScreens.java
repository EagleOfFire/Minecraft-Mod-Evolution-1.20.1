package ros.eagleoffire.rosevolution.screen.specialisationScreen.elementScreen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.TexturesScreen;

public class FutonScreens {
    private static final int leftPos = 0;
    private static final int topPos = 0;

    private static int rightButtonleftPos;
    private static int rightButtonTopPos;
    private static int rightButtonHeight;
    private static int rightButtonWidth;
    private static int leftButtonleftPos;
    private static int leftButtonTopPos;
    private static int leftButtonHeight;
    private static int leftButtonWidth;

    private static LocalPlayer player;
    private static Button buttonRight;
    private static Button buttonLeft;
    private static String element = "Futon";

    private static void init(int width, int height) {
        FutonScreens.rightButtonWidth = 20;
        FutonScreens.rightButtonHeight = (int) (1.56 * FutonScreens.rightButtonWidth);
        FutonScreens.rightButtonleftPos = width - FutonScreens.rightButtonWidth;
        FutonScreens.rightButtonTopPos = (height / 2) - (FutonScreens.rightButtonHeight / 2);

        FutonScreens.leftButtonWidth = 20;
        FutonScreens.leftButtonHeight = (int) (1.56 * FutonScreens.leftButtonWidth);
        FutonScreens.leftButtonleftPos = 0;
        FutonScreens.leftButtonTopPos = (height / 2) - (FutonScreens.leftButtonHeight / 2);
    }


    public static class FutonRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public FutonRangSScreen(LocalPlayer player) {
            super(TITLE);

            FutonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            FutonScreens.init(this.width, this.height);

            FutonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos, // X and Y position
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, FutonScreens.leftPos, FutonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (FutonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos,
                        0, 0,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class FutonRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public FutonRangAScreen(LocalPlayer player) {
            super(TITLE);

            FutonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            FutonScreens.init(this.width, this.height);

            FutonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos, // X and Y position
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "S"));
                        }
                    }));

            FutonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos, // X and Y position
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, FutonScreens.leftPos, FutonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (FutonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos,
                        0, 0,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (FutonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos,
                        0, 0,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class FutonRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public FutonRangBScreen(LocalPlayer player) {
            super(TITLE);

            FutonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            FutonScreens.init(this.width, this.height);

            FutonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos, // X and Y position
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "A"));
                        }
                    }));

            FutonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos, // X and Y position
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, FutonScreens.leftPos, FutonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (FutonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos,
                        0, 0,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (FutonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos,
                        0, 0,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class FutonRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public FutonRangCScreen(LocalPlayer player) {
            super(TITLE);

            FutonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            FutonScreens.init(this.width, this.height);

            FutonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos, // X and Y position
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "B"));
                        }
                    }));

            FutonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos, // X and Y position
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, FutonScreens.leftPos, FutonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (FutonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos,
                        0, 0,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (FutonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.leftButtonleftPos, FutonScreens.leftButtonTopPos,
                        0, 0,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight,
                        FutonScreens.leftButtonWidth, FutonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class FutonRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public FutonRangDScreen(LocalPlayer player) {
            super(TITLE);

            FutonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            FutonScreens.init(this.width, this.height);

            FutonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos, // X and Y position
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, FutonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, FutonScreens.leftPos, FutonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (FutonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        FutonScreens.rightButtonleftPos, FutonScreens.rightButtonTopPos,
                        0, 0,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight,
                        FutonScreens.rightButtonWidth, FutonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}

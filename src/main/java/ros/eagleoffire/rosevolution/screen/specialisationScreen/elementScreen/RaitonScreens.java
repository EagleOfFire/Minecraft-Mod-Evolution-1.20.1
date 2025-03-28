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

public class RaitonScreens {
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
    private static String element = "Raiton";

    private static void init(int width, int height) {
        RaitonScreens.rightButtonWidth = 20;
        RaitonScreens.rightButtonHeight = (int) (1.56 * RaitonScreens.rightButtonWidth);
        RaitonScreens.rightButtonleftPos = width - RaitonScreens.rightButtonWidth;
        RaitonScreens.rightButtonTopPos = (height / 2) - (RaitonScreens.rightButtonHeight / 2);

        RaitonScreens.leftButtonWidth = 20;
        RaitonScreens.leftButtonHeight = (int) (1.56 * RaitonScreens.leftButtonWidth);
        RaitonScreens.leftButtonleftPos = 0;
        RaitonScreens.leftButtonTopPos = (height / 2) - (RaitonScreens.leftButtonHeight / 2);
    }


    public static class RaitonRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public RaitonRangSScreen(LocalPlayer player) {
            super(TITLE);

            RaitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            RaitonScreens.init(this.width, this.height);

            RaitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos, // X and Y position
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, RaitonScreens.leftPos, RaitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (RaitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos,
                        0, 0,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class RaitonRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public RaitonRangAScreen(LocalPlayer player) {
            super(TITLE);

            RaitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            RaitonScreens.init(this.width, this.height);

            RaitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos, // X and Y position
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "S"));
                        }
                    }));

            RaitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos, // X and Y position
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, RaitonScreens.leftPos, RaitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (RaitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos,
                        0, 0,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (RaitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos,
                        0, 0,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class RaitonRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public RaitonRangBScreen(LocalPlayer player) {
            super(TITLE);

            RaitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            RaitonScreens.init(this.width, this.height);

            RaitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos, // X and Y position
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "A"));
                        }
                    }));

            RaitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos, // X and Y position
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, RaitonScreens.leftPos, RaitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (RaitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos,
                        0, 0,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (RaitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos,
                        0, 0,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class RaitonRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public RaitonRangCScreen(LocalPlayer player) {
            super(TITLE);

            RaitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            RaitonScreens.init(this.width, this.height);

            RaitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos, // X and Y position
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "B"));
                        }
                    }));

            RaitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos, // X and Y position
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, RaitonScreens.leftPos, RaitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (RaitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos,
                        0, 0,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (RaitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.leftButtonleftPos, RaitonScreens.leftButtonTopPos,
                        0, 0,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight,
                        RaitonScreens.leftButtonWidth, RaitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class RaitonRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public RaitonRangDScreen(LocalPlayer player) {
            super(TITLE);

            RaitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            RaitonScreens.init(this.width, this.height);

            RaitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos, // X and Y position
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, RaitonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, RaitonScreens.leftPos, RaitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (RaitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        RaitonScreens.rightButtonleftPos, RaitonScreens.rightButtonTopPos,
                        0, 0,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight,
                        RaitonScreens.rightButtonWidth, RaitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}

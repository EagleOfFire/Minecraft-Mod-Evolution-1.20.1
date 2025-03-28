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

public class SuitonScreens {
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
    private static String element = "Suiton";

    private static void init(int width, int height) {
        SuitonScreens.rightButtonWidth = 20;
        SuitonScreens.rightButtonHeight = (int) (1.56 * SuitonScreens.rightButtonWidth);
        SuitonScreens.rightButtonleftPos = width - SuitonScreens.rightButtonWidth;
        SuitonScreens.rightButtonTopPos = (height / 2) - (SuitonScreens.rightButtonHeight / 2);

        SuitonScreens.leftButtonWidth = 20;
        SuitonScreens.leftButtonHeight = (int) (1.56 * SuitonScreens.leftButtonWidth);
        SuitonScreens.leftButtonleftPos = 0;
        SuitonScreens.leftButtonTopPos = (height / 2) - (SuitonScreens.leftButtonHeight / 2);
    }


    public static class SuitonRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public SuitonRangSScreen(LocalPlayer player) {
            super(TITLE);

            SuitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            SuitonScreens.init(this.width, this.height);

            SuitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos, // X and Y position
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, SuitonScreens.leftPos, SuitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (SuitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos,
                        0, 0,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class SuitonRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public SuitonRangAScreen(LocalPlayer player) {
            super(TITLE);

            SuitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            SuitonScreens.init(this.width, this.height);

            SuitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos, // X and Y position
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "S"));
                        }
                    }));

            SuitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos, // X and Y position
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, SuitonScreens.leftPos, SuitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (SuitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos,
                        0, 0,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (SuitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos,
                        0, 0,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class SuitonRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public SuitonRangBScreen(LocalPlayer player) {
            super(TITLE);

            SuitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            SuitonScreens.init(this.width, this.height);

            SuitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos, // X and Y position
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "A"));
                        }
                    }));

            SuitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos, // X and Y position
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, SuitonScreens.leftPos, SuitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (SuitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos,
                        0, 0,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (SuitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos,
                        0, 0,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class SuitonRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public SuitonRangCScreen(LocalPlayer player) {
            super(TITLE);

            SuitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            SuitonScreens.init(this.width, this.height);

            SuitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos, // X and Y position
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "B"));
                        }
                    }));

            SuitonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos, // X and Y position
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, SuitonScreens.leftPos, SuitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (SuitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos,
                        0, 0,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (SuitonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.leftButtonleftPos, SuitonScreens.leftButtonTopPos,
                        0, 0,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight,
                        SuitonScreens.leftButtonWidth, SuitonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class SuitonRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public SuitonRangDScreen(LocalPlayer player) {
            super(TITLE);

            SuitonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            SuitonScreens.init(this.width, this.height);

            SuitonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos, // X and Y position
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, SuitonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, SuitonScreens.leftPos, SuitonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (SuitonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        SuitonScreens.rightButtonleftPos, SuitonScreens.rightButtonTopPos,
                        0, 0,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight,
                        SuitonScreens.rightButtonWidth, SuitonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}

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

public class DotonScreens {
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
    private static String element = "Doton";

    private static void init(int width, int height) {
        DotonScreens.rightButtonWidth = 20;
        DotonScreens.rightButtonHeight = (int) (1.56 * DotonScreens.rightButtonWidth);
        DotonScreens.rightButtonleftPos = width - DotonScreens.rightButtonWidth;
        DotonScreens.rightButtonTopPos = (height / 2) - (DotonScreens.rightButtonHeight / 2);

        DotonScreens.leftButtonWidth = 20;
        DotonScreens.leftButtonHeight = (int) (1.56 * DotonScreens.leftButtonWidth);
        DotonScreens.leftButtonleftPos = 0;
        DotonScreens.leftButtonTopPos = (height / 2) - (DotonScreens.leftButtonHeight / 2);
    }


    public static class DotonRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangSScreen(LocalPlayer player) {
            super(TITLE);

            DotonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height);

            DotonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos, // X and Y position
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, DotonScreens.leftPos, DotonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (DotonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos,
                        0, 0,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangAScreen(LocalPlayer player) {
            super(TITLE);

            DotonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height);

            DotonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos, // X and Y position
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "S"));
                        }
                    }));

            DotonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos, // X and Y position
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, DotonScreens.leftPos, DotonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (DotonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos,
                        0, 0,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (DotonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos,
                        0, 0,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangBScreen(LocalPlayer player) {
            super(TITLE);

            DotonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height);

            DotonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos, // X and Y position
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "A"));
                        }
                    }));

            DotonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos, // X and Y position
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, DotonScreens.leftPos, DotonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (DotonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos,
                        0, 0,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (DotonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos,
                        0, 0,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangCScreen(LocalPlayer player) {
            super(TITLE);

            DotonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height);

            DotonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos, // X and Y position
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "B"));
                        }
                    }));

            DotonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos, // X and Y position
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, DotonScreens.leftPos, DotonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (DotonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos,
                        0, 0,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (DotonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.leftButtonleftPos, DotonScreens.leftButtonTopPos,
                        0, 0,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight,
                        DotonScreens.leftButtonWidth, DotonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class DotonRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public DotonRangDScreen(LocalPlayer player) {
            super(TITLE);

            DotonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            DotonScreens.init(this.width, this.height);

            DotonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos, // X and Y position
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, DotonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, DotonScreens.leftPos, DotonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (DotonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        DotonScreens.rightButtonleftPos, DotonScreens.rightButtonTopPos,
                        0, 0,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight,
                        DotonScreens.rightButtonWidth, DotonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}

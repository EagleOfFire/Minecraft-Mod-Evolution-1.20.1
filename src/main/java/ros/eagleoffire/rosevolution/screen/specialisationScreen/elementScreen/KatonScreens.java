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

public class KatonScreens {
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
    private static String element = "Katon";

    private static void init(int width, int height) {
        KatonScreens.rightButtonWidth = 20;
        KatonScreens.rightButtonHeight = (int) (1.56 * KatonScreens.rightButtonWidth);
        KatonScreens.rightButtonleftPos = width - KatonScreens.rightButtonWidth;
        KatonScreens.rightButtonTopPos = (height / 2) - (KatonScreens.rightButtonHeight / 2);

        KatonScreens.leftButtonWidth = 20;
        KatonScreens.leftButtonHeight = (int) (1.56 * KatonScreens.leftButtonWidth);
        KatonScreens.leftButtonleftPos = 0;
        KatonScreens.leftButtonTopPos = (height / 2) - (KatonScreens.leftButtonHeight / 2);
    }


    public static class KatonRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KatonRangSScreen(LocalPlayer player) {
            super(TITLE);

            KatonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KatonScreens.init(this.width, this.height);

            KatonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos, // X and Y position
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, KatonScreens.leftPos, KatonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KatonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos,
                        0, 0,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KatonRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KatonRangAScreen(LocalPlayer player) {
            super(TITLE);

            KatonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KatonScreens.init(this.width, this.height);

            KatonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos, // X and Y position
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "S"));
                        }
                    }));

            KatonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos, // X and Y position
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, KatonScreens.leftPos, KatonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KatonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos,
                        0, 0,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (KatonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos,
                        0, 0,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KatonRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KatonRangBScreen(LocalPlayer player) {
            super(TITLE);

            KatonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KatonScreens.init(this.width, this.height);

            KatonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos, // X and Y position
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "A"));
                        }
                    }));

            KatonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos, // X and Y position
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, KatonScreens.leftPos, KatonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KatonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos,
                        0, 0,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (KatonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos,
                        0, 0,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KatonRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KatonRangCScreen(LocalPlayer player) {
            super(TITLE);

            KatonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KatonScreens.init(this.width, this.height);

            KatonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos, // X and Y position
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "B"));
                        }
                    }));

            KatonScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos, // X and Y position
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, KatonScreens.leftPos, KatonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KatonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos,
                        0, 0,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (KatonScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.leftButtonleftPos, KatonScreens.leftButtonTopPos,
                        0, 0,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight,
                        KatonScreens.leftButtonWidth, KatonScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KatonRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KatonRangDScreen(LocalPlayer player) {
            super(TITLE);

            KatonScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KatonScreens.init(this.width, this.height);

            KatonScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos, // X and Y position
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementRankScreen(player, KatonScreens.element, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, KatonScreens.leftPos, KatonScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KatonScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KatonScreens.rightButtonleftPos, KatonScreens.rightButtonTopPos,
                        0, 0,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight,
                        KatonScreens.rightButtonWidth, KatonScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}

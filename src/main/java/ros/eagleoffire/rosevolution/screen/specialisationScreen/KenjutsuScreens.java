package ros.eagleoffire.rosevolution.screen.specialisationScreen;

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

public class KenjutsuScreens {
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
    private static String element = "Kenjutsu";

    private static void init(int width, int height) {
        KenjutsuScreens.rightButtonWidth = 20;
        KenjutsuScreens.rightButtonHeight = (int) (1.56 * KenjutsuScreens.rightButtonWidth);
        KenjutsuScreens.rightButtonleftPos = width - KenjutsuScreens.rightButtonWidth;
        KenjutsuScreens.rightButtonTopPos = (height / 2) - (KenjutsuScreens.rightButtonHeight / 2);

        KenjutsuScreens.leftButtonWidth = 20;
        KenjutsuScreens.leftButtonHeight = (int) (1.56 * KenjutsuScreens.leftButtonWidth);
        KenjutsuScreens.leftButtonleftPos = 0;
        KenjutsuScreens.leftButtonTopPos = (height / 2) - (KenjutsuScreens.leftButtonHeight / 2);
    }


    public static class KenjutsuRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KenjutsuRangSScreen(LocalPlayer player) {
            super(TITLE);

            KenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KenjutsuScreens.init(this.width, this.height);

            KenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos, // X and Y position
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, KenjutsuScreens.leftPos, KenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KenjutsuRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KenjutsuRangAScreen(LocalPlayer player) {
            super(TITLE);

            KenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KenjutsuScreens.init(this.width, this.height);

            KenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos, // X and Y position
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "S"));
                        }
                    }));

            KenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos, // X and Y position
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, KenjutsuScreens.leftPos, KenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (KenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KenjutsuRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KenjutsuRangBScreen(LocalPlayer player) {
            super(TITLE);

            KenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KenjutsuScreens.init(this.width, this.height);

            KenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos, // X and Y position
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "A"));
                        }
                    }));

            KenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos, // X and Y position
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, KenjutsuScreens.leftPos, KenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (KenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KenjutsuRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KenjutsuRangCScreen(LocalPlayer player) {
            super(TITLE);

            KenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KenjutsuScreens.init(this.width, this.height);

            KenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos, // X and Y position
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "B"));
                        }
                    }));

            KenjutsuScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos, // X and Y position
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, KenjutsuScreens.leftPos, KenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (KenjutsuScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.leftButtonleftPos, KenjutsuScreens.leftButtonTopPos,
                        0, 0,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight,
                        KenjutsuScreens.leftButtonWidth, KenjutsuScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class KenjutsuRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public KenjutsuRangDScreen(LocalPlayer player) {
            super(TITLE);

            KenjutsuScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            KenjutsuScreens.init(this.width, this.height);

            KenjutsuScreens.buttonRight = addRenderableWidget(new ImageButton(
                    KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos, // X and Y position
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openKenjutsuScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, KenjutsuScreens.leftPos, KenjutsuScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (KenjutsuScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        KenjutsuScreens.rightButtonleftPos, KenjutsuScreens.rightButtonTopPos,
                        0, 0,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight,
                        KenjutsuScreens.rightButtonWidth, KenjutsuScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}

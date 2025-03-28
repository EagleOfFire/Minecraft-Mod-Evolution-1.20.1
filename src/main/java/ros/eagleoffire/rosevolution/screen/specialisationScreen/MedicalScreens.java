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

public class MedicalScreens {
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
    private static String element = "Medical";

    private static void init(int width, int height) {
        MedicalScreens.rightButtonWidth = 20;
        MedicalScreens.rightButtonHeight = (int) (1.56 * MedicalScreens.rightButtonWidth);
        MedicalScreens.rightButtonleftPos = width - MedicalScreens.rightButtonWidth;
        MedicalScreens.rightButtonTopPos = (height / 2) - (MedicalScreens.rightButtonHeight / 2);

        MedicalScreens.leftButtonWidth = 20;
        MedicalScreens.leftButtonHeight = (int) (1.56 * MedicalScreens.leftButtonWidth);
        MedicalScreens.leftButtonleftPos = 0;
        MedicalScreens.leftButtonTopPos = (height / 2) - (MedicalScreens.leftButtonHeight / 2);
    }


    public static class MedicalRangSScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public MedicalRangSScreen(LocalPlayer player) {
            super(TITLE);

            MedicalScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            MedicalScreens.init(this.width, this.height);

            MedicalScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos, // X and Y position
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "A"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_S_SCREEN, MedicalScreens.leftPos, MedicalScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (MedicalScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos,
                        0, 0,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class MedicalRangAScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public MedicalRangAScreen(LocalPlayer player) {
            super(TITLE);

            MedicalScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            MedicalScreens.init(this.width, this.height);

            MedicalScreens.buttonRight = addRenderableWidget(new ImageButton(
                    MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos, // X and Y position
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "S"));
                        }
                    }));

            MedicalScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos, // X and Y position
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "B"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_A_SCREEN, MedicalScreens.leftPos, MedicalScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (MedicalScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos,
                        0, 0,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (MedicalScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos,
                        0, 0,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class MedicalRangBScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public MedicalRangBScreen(LocalPlayer player) {
            super(TITLE);

            MedicalScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            MedicalScreens.init(this.width, this.height);

            MedicalScreens.buttonRight = addRenderableWidget(new ImageButton(
                    MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos, // X and Y position
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "A"));
                        }
                    }));

            MedicalScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos, // X and Y position
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_B_SCREEN, MedicalScreens.leftPos, MedicalScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (MedicalScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos,
                        0, 0,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (MedicalScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos,
                        0, 0,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class MedicalRangCScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public MedicalRangCScreen(LocalPlayer player) {
            super(TITLE);

            MedicalScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            MedicalScreens.init(this.width, this.height);

            MedicalScreens.buttonRight = addRenderableWidget(new ImageButton(
                    MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos, // X and Y position
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "B"));
                        }
                    }));

            MedicalScreens.buttonLeft = addRenderableWidget(new ImageButton(
                    MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos, // X and Y position
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "D"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_C_SCREEN, MedicalScreens.leftPos, MedicalScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (MedicalScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos,
                        0, 0,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
            if (MedicalScreens.buttonLeft.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.leftButtonleftPos, MedicalScreens.leftButtonTopPos,
                        0, 0,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight,
                        MedicalScreens.leftButtonWidth, MedicalScreens.leftButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    public static class MedicalRangDScreen extends Screen {
        private static final Component TITLE =
                Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

        public MedicalRangDScreen(LocalPlayer player) {
            super(TITLE);

            MedicalScreens.player = player;
        }

        @Override
        protected void init() {
            super.init();
            MedicalScreens.init(this.width, this.height);

            MedicalScreens.buttonRight = addRenderableWidget(new ImageButton(
                    MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos, // X and Y position
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openMedicalScreen(player, "C"));
                        }
                    }));
        }

        @Override
        public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            renderBackground(graphics);
            graphics.blit(TexturesScreen.RANG_D_SCREEN, MedicalScreens.leftPos, MedicalScreens.topPos, 0, 0, this.width, this.height, this.width, this.height);

            // Render buttons and other UI first
            super.render(graphics, mouseX, mouseY, partialTicks);

            // Ensure the hover texture is drawn above everything
            if (MedicalScreens.buttonRight.isHoveredOrFocused()) {
                graphics.pose().pushPose(); // Save previous render state
                graphics.pose().translate(0, 0, 500); // Move it to the top layer
                graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                        MedicalScreens.rightButtonleftPos, MedicalScreens.rightButtonTopPos,
                        0, 0,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight,
                        MedicalScreens.rightButtonWidth, MedicalScreens.rightButtonHeight);
                graphics.pose().popPose(); // Restore previous state
            }
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }
}

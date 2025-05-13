package ros.eagleoffire.rosevolution.screen.specialisationScreen.elementScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;
import ros.eagleoffire.rosevolution.ROSEvolution;
import ros.eagleoffire.rosevolution.client.ClientHooks;
import ros.eagleoffire.rosevolution.config.ModClientConfigs;
import ros.eagleoffire.rosevolution.screen.specialisationScreen.TexturesScreen;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public class SpellsScreens extends Screen {
    private static final int leftPos = 0;
    private static final int topPos = 0;

    private static int rightButtonLeftPos;
    private static int rightButtonTopPos;
    private static int rightButtonHeight;
    private static int rightButtonWidth;
    private static int leftButtonLeftPos;
    private static int leftButtonTopPos;
    private static int leftButtonHeight;
    private static int leftButtonWidth;
    private static int backButtonLeftPos;
    private static int backButtonTopPos;
    private static int backButtonHeight;
    private static int backButtonWidth;

    private static LocalPlayer player;
    private static Button buttonRight;
    private static Button buttonLeft;
    private static Button buttonBack;
    private static ImageButton imageButtonBack;

    private static ResourceLocation cachedTextureLocation;

    static List<String> spellTexts = ModClientConfigs.SPELLS_NAMES.get();
    static List<String> spellCommands = ModClientConfigs.SPELLS_COMMAND.get();
    static List<String> spellTextures = ModClientConfigs.SPELLS_ICON_RAW.get();
    static List<String> spellRanks = ModClientConfigs.SPELLS_RANKS.get();
    static List<String> spellCategories = ModClientConfigs.SPELLS_CATEGORIES.get();

    static int spellIconWidth;
    static int spellIconX;
    static int spellIconY;

    private String rank;
    private String element;

    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".test_screen");

    private static final Map<String, Integer> statusToNumberMap = new HashMap<>();
    private static final Map<Integer, String> numberToStatusMap = new HashMap<>();

    static {
        statusToNumberMap.put("D", 1);
        statusToNumberMap.put("C", 2);
        statusToNumberMap.put("B", 3);
        statusToNumberMap.put("A", 4);
        statusToNumberMap.put("S", 5);

        // Populate reverse map
        for (Map.Entry<String, Integer> entry : statusToNumberMap.entrySet()) {
            numberToStatusMap.put(entry.getValue(), entry.getKey());
        }
    }

    public SpellsScreens(String rank, String element) {
        super(TITLE);
        this.rank = rank;
        this.element = element;
    }

    @Override
    protected void init() {
        super.init();
        rightButtonWidth = 20;
        rightButtonHeight = (int) (1.56 * rightButtonWidth);
        rightButtonLeftPos = width - rightButtonWidth;
        rightButtonTopPos = (height / 2) - (rightButtonHeight / 2);

        leftButtonWidth = 20;
        leftButtonHeight = (int) (1.56 * leftButtonWidth);
        leftButtonLeftPos = 0;
        leftButtonTopPos = (height / 2) - (leftButtonHeight / 2);

        backButtonWidth = 40;
        backButtonHeight = (int) (0.625 * backButtonWidth);
        backButtonLeftPos = backButtonWidth / 2;
        backButtonTopPos = height - backButtonHeight - (backButtonWidth / 2);

        imageButtonBack = new ImageButton(
                backButtonLeftPos, backButtonTopPos, // X and Y position
                backButtonWidth, backButtonHeight, // Width and Height
                0, 0, // Texture X and Y (start of image)
                0, // Amount to shift Y on click
                TexturesScreen.BACK_ARROW, // Texture location
                backButtonWidth, backButtonHeight, // Total texture size (width, height of full image)
                (button) -> {
                    if (minecraft != null && minecraft.player != null) {
                        if (element.equals("Katon") || element.equals("Suiton") || element.equals("Doton") || element.equals("Fuuton") || element.equals("Raiton")) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openElementSelectScreen(player));
                        } else {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openSpecialisationScreen(player));
                        }
                    }
                });

        spellIconWidth = width / 15;
        spellIconX = (width - (5 * spellIconWidth)) / 6;
        spellIconY = height / 3;

        WidgetAdder adder = this::addRenderableWidget;

        if (!Objects.equals(rank, "S")) {
            buttonRight = addRenderableWidget(new ImageButton(
                    rightButtonLeftPos, rightButtonTopPos, // X and Y position
                    rightButtonWidth, rightButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.RIGHT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    rightButtonWidth, rightButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openSpellSelectScreen(element, numberToStatus(statusToNumber(rank) + 1)));
                        }
                    }));
        }

        if (!Objects.equals(rank, "D")) {
            buttonLeft = addRenderableWidget(new ImageButton(
                    leftButtonLeftPos, leftButtonTopPos, // X and Y position
                    leftButtonWidth, leftButtonHeight, // Width and Height
                    0, 0, // Texture X and Y (start of image)
                    0, // Amount to shift Y on click
                    TexturesScreen.LEFT_BUTTON_TEXTURE_DEFAULT, // Texture location
                    leftButtonWidth, leftButtonHeight, // Total texture size (width, height of full image)
                    (button) -> {
                        if (this.minecraft != null && this.minecraft.player != null) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openSpellSelectScreen(element, numberToStatus(statusToNumber(rank) - 1)));
                        }
                    }));
        }

        buttonBack = addRenderableWidget(imageButtonBack);
        int a = 0;
        for (int i = 0; i < spellTexts.size() && i < spellCommands.size(); i++) {
            if (spellRanks.get(i).equals(rank) && spellCategories.get(i).equals(element)) {
                String textureName = spellTextures.get(i);
                DynamicTexture cachedTexture = ModClientConfigs.getButtonImages().get(textureName);

                if (cachedTexture != null) {
                    cachedTextureLocation = new ResourceLocation("modid", "dynamic_textures/" + textureName);
                    Minecraft.getInstance().getTextureManager().register(cachedTextureLocation, cachedTexture);
                }

                String command = spellCommands.get(i);

                if (cachedTextureLocation != null) {
                    loadButtonsFromConfig(adder, this, cachedTextureLocation, spellIconX, spellIconY, spellIconWidth, command);
                }
                if ((a + 1) % 5 == 0 && a != 0) {  // Every 5th iteration (excluding 0)
                    spellIconX = 0;
                    spellIconY += spellIconWidth + ((this.height - (this.height / 3)) - (3 * spellIconWidth)) / 4; // Adjust the increment as needed
                } else {
                    spellIconX += spellIconWidth + (this.width - (5 * spellIconWidth)) / 6; // Adjust the increment as needed
                }
                a++;
            }
        }
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        switch (rank) {
            case "S":
                graphics.blit(TexturesScreen.RANG_S_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
                break;
            case "A":
                graphics.blit(TexturesScreen.RANG_A_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
                break;
            case "B":
                graphics.blit(TexturesScreen.RANG_B_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
                break;
            case "C":
                graphics.blit(TexturesScreen.RANG_C_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
                break;
            default:
                graphics.blit(TexturesScreen.RANG_D_SCREEN, leftPos, topPos, 0, 0, this.width, this.height, this.width, this.height);
        }
        butonBackHover(graphics);

        if (!Objects.equals(rank, "S")) {
            buttonRightHover(graphics);
        }
        if (!Objects.equals(rank, "D")) {
            buttonLeftHover(graphics);
        }
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    private static void executeCommand(String command, Minecraft minecraft) {
        if (minecraft != null && minecraft.player != null && !command.isEmpty()) {
            minecraft.player.connection.sendCommand(command);
        }
    }

    public static void loadButtonsFromConfig(WidgetAdder adder, Screen screen, ResourceLocation cachedTextureLocation, int spellIconX, int spellIconY, int spellIconWidth, String command) {
        ImageButton button = new ImageButton(
                spellIconX, spellIconY, // X and Y position
                spellIconWidth, spellIconWidth, // Width and Height
                0, 0, // Texture X and Y (start of image)
                0, // Amount to shift Y on click
                cachedTextureLocation, // Texture location
                spellIconWidth, spellIconWidth, // Total texture size (width, height of full image)
                (b) -> {
                    screen.getMinecraft();
                    if (screen.getMinecraft().player != null) {
                        String playerName = screen.getMinecraft().player.getGameProfile().getName();
                        String processedCommand = command.replace("@s", playerName);
                        executeCommand(processedCommand, screen.getMinecraft());
                    }
                });

        adder.addWidget(button);
    }

    private static void butonBackHover(GuiGraphics graphics) {
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
    }

    private static void buttonRightHover(GuiGraphics graphics) {
        // Ensure the hover texture is drawn above everything
        if (buttonRight.isHoveredOrFocused()) {
            graphics.pose().pushPose(); // Save previous render state
            graphics.pose().translate(0, 0, 500); // Move it to the top layer
            graphics.blit(TexturesScreen.RIGHT_BUTTON_TEXTURE_HOVER,
                    rightButtonLeftPos, rightButtonTopPos,
                    0, 0,
                    rightButtonWidth, rightButtonHeight,
                    rightButtonWidth, rightButtonHeight);
            graphics.pose().popPose(); // Restore previous state
        }
    }

    private static void buttonLeftHover(GuiGraphics graphics) {
        if (buttonLeft.isHoveredOrFocused()) {
            graphics.pose().pushPose(); // Save previous render state
            graphics.pose().translate(0, 0, 500); // Move it to the top layer
            graphics.blit(TexturesScreen.LEFT_BUTTON_TEXTURE_HOVER,
                    leftButtonLeftPos, leftButtonTopPos,
                    0, 0,
                    leftButtonWidth, leftButtonHeight,
                    leftButtonWidth, leftButtonHeight);
            graphics.pose().popPose(); // Restore previous state
        }
    }

    @FunctionalInterface
    public interface WidgetAdder {
        void addWidget(AbstractWidget widget);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static int statusToNumber(String status) {
        Integer value = statusToNumberMap.get(status);
        if (value == null) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        return value;
    }

    public static String numberToStatus(int number) {
        String status = numberToStatusMap.get(number);
        if (status == null) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }
        return status;
    }

}
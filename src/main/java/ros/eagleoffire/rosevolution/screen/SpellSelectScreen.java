package ros.eagleoffire.rosevolution.screen;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import ros.eagleoffire.rosevolution.ROSEvolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class SpellSelectScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + ROSEvolution.MODID + ".spell_select_screen");

    private static final ResourceLocation TEXTURE = new ResourceLocation(ROSEvolution.MODID, "textures/gui/spell_select_screen.png");

    private final LocalPlayer player;
    private final int imageWidht, imageHeight;
    private int leftPos,topPos;

    public SpellSelectScreen(LocalPlayer player) {
        super(TITLE);
        this.player = player;
        this.imageWidht = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidht) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if(this.minecraft == null) return;

        loadButtonsFromConfig();

    }

    private void loadButtonsFromConfig() {
        Path configPath = Paths.get(Minecraft.getInstance().gameDirectory.getPath(), "config/rosevolution/spell_menu.json");
        if (!Files.exists(configPath)) return;

       try {
           String json = Files.readString(configPath);
           List<Map<String, String>> buttonConfigs = new Gson().fromJson(json, new TypeToken<List<Map<String, String>>>() {}.getType());
           int buttonY = this.topPos + 20;

           for (Map<String, String> config : buttonConfigs) {
               String text = config.getOrDefault("text", "Button");
               String command = config.getOrDefault("command", "");
               String imagePath = config.getOrDefault("image", "textures/gui/default_button.png");

               ResourceLocation image = new ResourceLocation(ROSEvolution.MODID, imagePath);

               if(imagePath.endsWith(".png")) {
                   addRenderableWidget(new ImageButton(
                           this.leftPos + 8 , buttonY, 80, 20,
                           0,0,20,
                           image, 80, 40,
                           btn -> executeCommand(command)
                   ));
               } else {
                   addRenderableWidget(Button.builder(Component.literal(text), btn -> executeCommand(command))
                           .bounds(this.leftPos + 8, buttonY, 80, 20)
                           .tooltip(Tooltip.create(Component.literal(text)))
                           .build());
               }
               buttonY += 25;
           }
       } catch (IOException e){
           e.printStackTrace();
       }
    }

    private void executeCommand(String command){
        if(this.minecraft != null && this.minecraft.player != null && !command.isEmpty()){
            this.minecraft.player.connection.sendCommand(command);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0,0, this.imageWidht, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font,
                TITLE,
                this.leftPos + 8,
                this.topPos + 8,
                0x404040,
                false);

        graphics.drawString(this.font,
                "Player Name: " + this.player.getScoreboardName(),
                this.leftPos + 8,
                this.topPos + 45,
                0xFF0000,
                false);

        //TODO draw the background
        //TODO draw nodes in a tree-like structure similar to advancements
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

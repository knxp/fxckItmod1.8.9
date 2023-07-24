package pxnk.mutemod;

import org.lwjgl.input.Keyboard;

import mutemod.commands.MutePos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import pxnk.mutemod.Render;

import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.RenderGameOverlayEvent;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MuteMod {

	private final Render renderEventHandler = new Render();
    public static final String MODID = "FI";
    public static final String VERSION = "1.0";
    private static final KeyBinding MUTE = new KeyBinding("Toggle Mute", Keyboard.KEY_M, "Keyn Mute Bind");
    private float recordedSoundLevel;

    @EventHandler
    public void init(FMLInitializationEvent e) {
    	   ClientRegistry.registerKeyBinding(MUTE);
           MinecraftForge.EVENT_BUS.register(this);
           MinecraftForge.EVENT_BUS.register(renderEventHandler);
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    	// command
    	event.registerServerCommand(new MutePos());
    }
    

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e) {
      
    	if (!MUTE.isKeyDown()) {
            return;
        }

        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        float soundLevel = settings.getSoundLevel(SoundCategory.MASTER);
        
        
        if (soundLevel == 0) {
        	renderEventHandler.showTextOnScreen("Unmuted");
            settings.setSoundLevel(SoundCategory.MASTER, recordedSoundLevel);
        } else {
        	
        	renderEventHandler.showTextOnScreen("Muted");
            this.recordedSoundLevel = soundLevel;
            settings.setSoundLevel(SoundCategory.MASTER, 0);
        }
    }
}
package pxnk.fxckit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.settings.KeyBinding;



public class Render {

	    private long startTime;
	    private final int displayDuration = 1000; // text display time
	    private boolean showText;
	    private String textToDisplay;

	    public void showTextOnScreen(String text) {
	        startTime = System.currentTimeMillis();
	        showText = true;
	        textToDisplay = text;
	    }
	
	@SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) throws InterruptedException {
   
		final int RightX = 0;
		final int LeftX = 0;
		final int TopY = 0;
		final int BottomY = 0;
		
		 if (showText) {
	            long currentTime = System.currentTimeMillis();
	            long elapsedTime = currentTime - startTime;

	            if (elapsedTime < displayDuration) {
	            	
	            	if(textToDisplay == "Muted") {
	            		
	            		FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
				        fRender.drawString(EnumChatFormatting.RED + textToDisplay, 430, 10, 0);
	            		
	            	}else {
	            		
	            		FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
				        fRender.drawString(EnumChatFormatting.GREEN + textToDisplay, 430, 10, 0);
	            	}
	            } else {
	                showText = false;
	            }
	        }
        }
}

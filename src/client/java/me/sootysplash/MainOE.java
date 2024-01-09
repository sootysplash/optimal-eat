package me.sootysplash;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainOE implements ClientModInitializer {
    MinecraftClient mc = MinecraftClient.getInstance();
    int activeItemCount = 0;
    public static final Logger LOGGER = LoggerFactory.getLogger("OptimalEat");
    @Override
    public void onInitializeClient() {
        AutoConfig.register(ConfigOE.class, GsonConfigSerializer::new);
        LOGGER.info("OptimalEat | Sootysplash was here");
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (mc.player == null) {
                return;
            }
//            mc.inGameHud.getChatHud().addMessage(Text.of(String.valueOf(mc.player.getItemUseTime())));
            activeItemCount = mc.player.getActiveItem().getCount() != 0 ? mc.player.getActiveItem().getCount() : activeItemCount;
            if(mc.player.getItemUseTimeLeft() <= 0 && mc.options.useKey.isPressed() && ConfigOE.getInstance().enabled && activeItemCount <= ConfigOE.getInstance().maxCount){
                mc.options.useKey.setPressed(false);
            }
        });
    }
}
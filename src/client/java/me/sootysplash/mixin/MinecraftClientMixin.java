package me.sootysplash.mixin;

import me.sootysplash.ConfigOE;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.sootysplash.MainOE.updatedInventory;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void onPostInputs(CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player != null) {
            if (!mc.player.getActiveItem().isEmpty() && mc.player.getUseItemRemainingTicks() <= 0 && mc.options.keyUse.isDown() && updatedInventory && ConfigOE.getInstance().enabled) {
                mc.options.keyUse.setDown(false);
                updatedInventory = false;
            }
        }
    }
}

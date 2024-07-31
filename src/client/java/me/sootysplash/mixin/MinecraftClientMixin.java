package me.sootysplash.mixin;

import me.sootysplash.ConfigOE;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.sootysplash.MainOE.updatedInventory;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void onPostInputs(CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(mc != null && mc.player != null) {
            if (!mc.player.getActiveItem().isEmpty() && mc.player.getItemUseTimeLeft() <= 0 && mc.options.useKey.isPressed() && updatedInventory && ConfigOE.getInstance().enabled) {
                mc.options.useKey.setPressed(false);
                updatedInventory = false;
            }
        }
    }
}

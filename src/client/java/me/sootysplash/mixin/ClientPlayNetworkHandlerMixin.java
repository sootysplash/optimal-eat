package me.sootysplash.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.sootysplash.MainOE.updatedInventory;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onScreenHandlerSlotUpdate", at = @At("TAIL"))
    private void onSlot(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci) {
        if (!updatedInventory)
            updatedInventory = true;
    }

    @Inject(method = "onInventory", at = @At("TAIL"))
    private void onInventoryPacket(InventoryS2CPacket packet, CallbackInfo ci) {
        if (!updatedInventory)
            updatedInventory = true;
    }
}

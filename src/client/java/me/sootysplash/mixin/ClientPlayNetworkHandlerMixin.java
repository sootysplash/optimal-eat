package me.sootysplash.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.network.protocol.game.ClientboundSetPlayerInventoryPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.sootysplash.MainOE.updatedInventory;

@Mixin(ClientPacketListener.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "handleContainerSetSlot", at = @At("TAIL"))
    private void onSlot(ClientboundContainerSetSlotPacket packet, CallbackInfo ci) {
        if (!updatedInventory)
            updatedInventory = true;
    }

    @Inject(method = "handleSetPlayerInventory", at = @At("TAIL"))
    private void onInventoryPacket(ClientboundSetPlayerInventoryPacket packet, CallbackInfo ci) {
        if (!updatedInventory)
            updatedInventory = true;
    }
}

package com.nanaios.curiosable_ammo_box.mixin;

import com.nanaios.curiosable_ammo_box.util.InventoryWithCurios;
import com.tacz.guns.client.gui.overlay.GunHudOverlay;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = GunHudOverlay.class,remap = false)
public class MixinGunHudOverlay {
    @ModifyVariable(method = "handleInventoryAmmo", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private static Inventory mixinHandleInventoryAmmo(Inventory inventory) {
        return new InventoryWithCurios(inventory);
    }
}

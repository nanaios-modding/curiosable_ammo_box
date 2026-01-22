package com.nanaios.curiosable_ammo_box.mixin.taczaddon;

import com.mafuyu404.taczaddon.init.VirtualInventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = VirtualInventory.ItemHandler.class, remap = false)
public interface MixinCuriosableAmmoBoxVirtualInventory$ItemHandler {
}

package com.nanaios.curios_for_ammo_box.mixin.taczaddon;

import com.mafuyu404.taczaddon.init.VirtualInventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = VirtualInventory.ItemHandler.class, remap = false)
public interface MixinVirtualInventory$ItemHandler {
    @Final
    @Accessor(value = "virtualInventory")
    VirtualInventory mixinCfAB$getVirtualInventory();
}

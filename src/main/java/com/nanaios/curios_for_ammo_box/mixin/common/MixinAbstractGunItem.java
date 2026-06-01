package com.nanaios.curios_for_ammo_box.mixin.common;

import com.nanaios.curios_for_ammo_box.util.ItemHandlerWithCurios;
import com.tacz.guns.api.item.gun.AbstractGunItem;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = AbstractGunItem.class, remap = false)
public class MixinAbstractGunItem {

    @ModifyVariable(method = "lambda$canReload$1", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private static IItemHandler wrapItemHandlerForCanReload(IItemHandler itemHandler) {
        return ItemHandlerWithCurios.create(itemHandler);
    }
    @ModifyVariable(method = "findAndExtractInventoryAmmo", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private IItemHandler wrapItemHandlerForFindAndExtractInventoryAmmo(IItemHandler itemHandler) {
        return ItemHandlerWithCurios.create(itemHandler);
    }

    @ModifyVariable(method = "lambda$hasInventoryAmmo$6", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private static IItemHandler wrapItemHandlerForHasInventoryAmmo(IItemHandler itemHandler) {
        return ItemHandlerWithCurios.create(itemHandler);
    }
}

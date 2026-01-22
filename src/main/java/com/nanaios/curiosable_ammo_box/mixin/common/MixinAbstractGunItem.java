package com.nanaios.curiosable_ammo_box.mixin.common;

import com.nanaios.curiosable_ammo_box.util.PlayerInvWrapperWithCurios;
import com.tacz.guns.api.item.gun.AbstractGunItem;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = AbstractGunItem.class,remap = false)
public class MixinAbstractGunItem {

    @ModifyVariable(method = "lambda$canReload$1", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private static IItemHandler mixinAbstractGunItem$lambda$canReload$1(IItemHandler itemHandler) {
        return PlayerInvWrapperWithCurios.create(itemHandler);
    }
    @ModifyVariable(method = "findAndExtractInventoryAmmo", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private IItemHandler MixinAbstractGunItem$findAndExtractInventoryAmmo(IItemHandler itemHandler) {
        return PlayerInvWrapperWithCurios.create(itemHandler);
    }

    @ModifyVariable(method = "lambda$hasInventoryAmmo$6", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private static IItemHandler MixinAbstractGunItem$lambda$hasInventoryAmmo$6(IItemHandler itemHandler) {
        return PlayerInvWrapperWithCurios.create(itemHandler);
    }
}

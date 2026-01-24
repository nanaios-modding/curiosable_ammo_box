package com.nanaios.curiosable_ammo_box.mixin.common;

import com.nanaios.curiosable_ammo_box.util.ItemHandlerWithCurios;
import com.tacz.guns.client.animation.statemachine.GunAnimationStateContext;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = GunAnimationStateContext.class,remap = false)
public class MixinGunAnimationStateContext {
    @ModifyVariable(method = "lambda$hasAmmoToConsume$7", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private static IItemHandler MixinGunAnimationStateContext$lambda$hasAmmoToConsume$7(IItemHandler itemHandler) {
        return ItemHandlerWithCurios.create(itemHandler);
    }
}

package com.nanaios.curiosable_ammo_box.mixin.common;

import com.nanaios.curiosable_ammo_box.util.PlayerInvWrapperWithCurios;
import com.tacz.guns.item.ModernKineticGunScriptAPI;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ModernKineticGunScriptAPI.class,remap = false)
public class MixinModernKineticGunScriptAPI {
    @ModifyVariable(method = "lambda$hasAmmoToConsume$5", at = @At("HEAD"), argsOnly = true, name = "arg1")
    private IItemHandler MixinModernKineticGunScriptAPI$lambda$hasAmmoToConsume$5(IItemHandler itemHandler) {
        return PlayerInvWrapperWithCurios.create(itemHandler);
    }
}

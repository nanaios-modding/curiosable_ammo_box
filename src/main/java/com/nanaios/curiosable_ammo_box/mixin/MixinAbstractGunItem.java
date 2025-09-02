package com.nanaios.curiosable_ammo_box.mixin;

import com.nanaios.curiosable_ammo_box.util.InvWrapper;
import com.tacz.guns.api.item.gun.AbstractGunItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = AbstractGunItem.class,remap = false)
public class MixinAbstractGunItem {
    @ModifyVariable(method = "lambda$canReload$1", at = @At("HEAD"),ordinal = 1, argsOnly = true)
    private static IItemHandler testNN(IItemHandler itemHandler) {
        return InvWrapper.create(itemHandler);
    }
}

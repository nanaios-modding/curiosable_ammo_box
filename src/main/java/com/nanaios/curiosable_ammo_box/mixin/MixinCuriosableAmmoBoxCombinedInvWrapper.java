package com.nanaios.curiosable_ammo_box.mixin;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CombinedInvWrapper.class)
public interface MixinCuriosableAmmoBoxCombinedInvWrapper {
    @Final
    @Accessor(value = "itemHandler")
    IItemHandlerModifiable[] mixinCuriosableAmmoBoxItemHandler();
}

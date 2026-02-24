package com.nanaios.curios_for_ammo_box.mixin.common;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = CombinedInvWrapper.class,remap = false)
public interface MixinCombinedInvWrapper {
    @Final
    @Accessor(value = "itemHandler")
    IItemHandlerModifiable[] mixinCFAB$getItemHandler();
}

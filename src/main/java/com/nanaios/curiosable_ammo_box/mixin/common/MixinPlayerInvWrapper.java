package com.nanaios.curiosable_ammo_box.mixin.common;

import com.nanaios.curiosable_ammo_box.util.ICuriosHandlerSourceMarker;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = PlayerInvWrapper.class,remap = false)
public class MixinPlayerInvWrapper implements ICuriosHandlerSourceMarker {
    @Override
    public int CfAB$getSlots() {
        return ((PlayerInvWrapper)(Object)this).getSlots();
    }

    @Override
    public @NotNull ItemStack CfAB$getStackInSlot(int slot) {
        return ((PlayerInvWrapper)(Object)this).getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack CfAB$extractItem(int slot, int amount, boolean simulate) {
        return ((PlayerInvWrapper)(Object)this).extractItem(slot,amount,simulate);
    }
}

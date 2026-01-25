package com.nanaios.curios_for_ammo_box.mixin.common;

import com.nanaios.curios_for_ammo_box.util.ICuriosHandlerSourceMarker;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Inventory.class)
public class MixinInventory implements ICuriosHandlerSourceMarker {

    @Override
    public int CfAB$getSlots() {
        return ((Inventory)(Object)this).getContainerSize();
    }

    @Override
    public @NotNull ItemStack CfAB$getStackInSlot(int slot) {
        return ((Inventory)(Object)this).getItem(slot);
    }

    @Override
    public @NotNull ItemStack CfAB$extractItem(int slot, int amount, boolean simulate) {
        return ItemStack.EMPTY;
    }
}

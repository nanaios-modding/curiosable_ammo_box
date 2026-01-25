package com.nanaios.curios_for_ammo_box.mixin.common;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Inventory.class)
public class MixinInventory implements IItemHandler {
    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int i) {
        return null;
    }

    @Override
    public @NotNull ItemStack insertItem(int i, @NotNull ItemStack itemStack, boolean b) {
        return null;
    }

    @Override
    public @NotNull ItemStack extractItem(int i, int i1, boolean b) {
        return null;
    }

    @Override
    public int getSlotLimit(int i) {
        return 0;
    }

    @Override
    public boolean isItemValid(int i, @NotNull ItemStack itemStack) {
        return false;
    }
}

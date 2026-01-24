package com.nanaios.curiosable_ammo_box.util;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ICuriosHandlerSourceMarker {
    int CfAB$getSlots();
    @NotNull ItemStack CfAB$getStackInSlot(int slot);
}

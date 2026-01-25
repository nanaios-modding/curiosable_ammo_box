package com.nanaios.curios_for_ammo_box.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryWithCurios extends Inventory {
    final CuriosHandler handler;
    public InventoryWithCurios(Inventory inventory) {
        super(inventory.player);
        this.handler = new CuriosHandler((ICuriosHandlerSourceMarker) inventory,inventory.player);
    }

    @Override
    public int getContainerSize() {
        return handler.getSlots();
    }

    @Override
    public @NotNull ItemStack getItem(int index) {
        return handler.getStackInSlot(index);
    }
}

package com.nanaios.curiosable_ammo_box.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.LinkedHashSet;
import java.util.Map;

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

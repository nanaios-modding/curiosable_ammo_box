package com.nanaios.curiosable_ammo_box.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class InventoryWithCurios extends Inventory {
    private final ICuriosItemHandler curiosInventory;
    private final Inventory overrideInventory;
    public InventoryWithCurios(Player player, Inventory inventory) {
        super(player);
        this.overrideInventory = inventory;
        this.curiosInventory = CuriosApi.getCuriosInventory(player).resolve().get();
    }

    @Override
    public int getContainerSize() {
        int defaultContainerSize = overrideInventory.getContainerSize();
        int curiosSize = curiosInventory.getSlots();

        return defaultContainerSize + curiosSize;
    }

    private ItemStack getItemFromCurios(int index) {
        int count = 0;
        for(Map.Entry<String, ICurioStacksHandler> curiosEntry:curiosInventory.getCurios().entrySet()){
            IDynamicStackHandler handler= curiosEntry.getValue().getStacks();
            for(int j = 0;j < handler.getSlots();j++) {
                if(index == count) return  handler.getStackInSlot(j);
                count++;
            }
        }
        return ItemStack.EMPTY;
    }


    @Override
    public ItemStack getItem(int index) {
        int playerContainerSize = overrideInventory.getContainerSize();

        if(index < playerContainerSize) {
            return overrideInventory.getItem(index);
        }

        return getItemFromCurios(index - playerContainerSize);
    }
}

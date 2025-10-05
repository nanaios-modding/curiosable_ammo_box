package com.nanaios.curiosable_ammo_box.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.LinkedHashSet;
import java.util.Map;

public class InventoryWithCurios extends Inventory {
    private @Nullable ICuriosItemHandler curiosInventory;
    private final Inventory overrideInventory;

    public InventoryWithCurios(Inventory inventory) {
        super(inventory.player);
        this.overrideInventory = inventory;
        CuriosApi.getCuriosInventory(player).ifPresent(iCuriosItemHandler -> {
            curiosInventory = iCuriosItemHandler;
        });
    }

    @Override
    public int getContainerSize() {
        int defaultContainerSize = overrideInventory.getContainerSize();
        int curiosSize = curiosInventory != null ? curiosInventory.getSlots() : 0;
        int playerInventorySize = player.getInventory().offhand.size();

        return defaultContainerSize + curiosSize + playerInventorySize;
    }

    private ItemStack getItemFromCurios(int index) {
        if(curiosInventory == null) return ItemStack.EMPTY;

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
    public @NotNull ItemStack getItem(int index) {
        //indexが0以上overrideInventory.getContainerSize()未満ならoverrideInventoryから取得
        int overrideInventoryContainerSize = overrideInventory.getContainerSize();
        if(index < overrideInventoryContainerSize) {
            return overrideInventory.getItem(index);
        }

        //indexがoverrideInventory.getContainerSize()以上curiosInventory.getSlots() + overrideInventory.getContainerSize()未満ならcuriosInventoryから取得
        int curiosSize = curiosInventory != null ? curiosInventory.getSlots() : 0;
        curiosSize += overrideInventoryContainerSize;
        if(index < curiosSize) {
            return getItemFromCurios(index - overrideInventoryContainerSize);
        }

        //indexがcuriosInventory.getSlots() + overrideInventory.getContainerSize()以上ならplayerInventoryから取得
        return player.getInventory().offhand.get(index - curiosSize);
    }
}

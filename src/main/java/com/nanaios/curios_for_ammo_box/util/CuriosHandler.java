package com.nanaios.curios_for_ammo_box.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class CuriosHandler {
    final ICuriosHandlerSourceMarker source;
    final Player player;
    @Nullable ICuriosItemHandler curiosInventory;

    public <T extends ICuriosHandlerSourceMarker>CuriosHandler(T source, Player player) {
        this.source = source;
        this.player = player;
        CuriosApi.getCuriosInventory(player).ifPresent(iCuriosItemHandler -> {;
            curiosInventory = iCuriosItemHandler;
        });
    }

    public int getSlots() {
        int defaultContainerSize = source.CfAB$getSlots();
        int curiosSize = curiosInventory != null ? curiosInventory.getSlots() : 0;

        return defaultContainerSize + curiosSize;
    }

    public ItemStack getItemFromCurios(int slot) {
        if(curiosInventory == null) return ItemStack.EMPTY;

        int count = 0;
        for(Map.Entry<String, ICurioStacksHandler> curiosEntry:curiosInventory.getCurios().entrySet()){
            IDynamicStackHandler handler = curiosEntry.getValue().getStacks();
            for(int j = 0;j < handler.getSlots();j++) {
                if(slot == count) return handler.getStackInSlot(j);
                count++;
            }
        }
        return ItemStack.EMPTY;
    }

    public ItemStack extractItemFromCurios(int slot, int amount, boolean simulate) {
        if(curiosInventory == null) return ItemStack.EMPTY;

        int count = 0;
        for(Map.Entry<String, ICurioStacksHandler> curiosEntry:curiosInventory.getCurios().entrySet()){
            IDynamicStackHandler handler = curiosEntry.getValue().getStacks();
            for(int j = 0;j < handler.getSlots();j++) {
                if(slot == count) return handler.extractItem(slot, amount, simulate);
                count++;
            }
        }
        return ItemStack.EMPTY;
    }


    public @NotNull ItemStack getStackInSlot(int slot) {
        int overrideInventoryContainerSize = source.CfAB$getSlots();
        if(slot < overrideInventoryContainerSize) {
            return source.CfAB$getStackInSlot(slot);
        }

        //curiosInventoryから取得
        return getItemFromCurios(slot - overrideInventoryContainerSize);
    }

    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        int overrideInventoryContainerSize = source.CfAB$getSlots();
        if(slot < overrideInventoryContainerSize) {
            return source.CfAB$extractItem(slot, amount, simulate);
        }

        return extractItemFromCurios(slot - overrideInventoryContainerSize, amount, simulate);
    }
}

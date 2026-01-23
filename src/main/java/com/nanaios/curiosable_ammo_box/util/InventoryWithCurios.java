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

        return defaultContainerSize + curiosSize;
    }

    private ItemStack getItemFromCurios(int index) {
        if(curiosInventory == null) return ItemStack.EMPTY;


        int count = 0;
        for(Map.Entry<String, ICurioStacksHandler> curiosEntry:curiosInventory.getCurios().entrySet()){
            IDynamicStackHandler handler = curiosEntry.getValue().getStacks();
            for(int j = 0;j < handler.getSlots();j++) {
                if(index == count) return handler.getStackInSlot(j);
                count++;
            }
        }
        return ItemStack.EMPTY;
    }


    @Override
    public @NotNull ItemStack getItem(int index) {
        //indexがoverrideInventory.getContainerSize()未満ならoverrideInventoryから取得
        int overrideInventoryContainerSize = overrideInventory.getContainerSize();
        if(index < overrideInventoryContainerSize) {
            return overrideInventory.getItem(index);
        }

        //curiosInventoryから取得
        return getItemFromCurios(index - overrideInventoryContainerSize);
    }

    public static class ItemHandler implements IItemHandler {


        @Override
        public int getSlots() {
            return 0;
        }

        @Override
        public @NotNull ItemStack getStackInSlot(int slot) {
            return null;
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return null;
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return null;
        }

        @Override
        public int getSlotLimit(int slot) {
            return 0;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return false;
        }
    }

    public static IItemHandler create(IItemHandler itemHandler) {
        if(itemHandler instanceof  PlayerInvWrapper playerWap) {
            IItemHandlerModifiable[] handlers = playerWap.itemHandler;
            if(handlers[0] instanceof PlayerMainInvWrapper mainWap) {
                return new PlayerInvWrapperWithCurios(mainWap.getInventoryPlayer());
            }
        }
        return itemHandler;
    }
}

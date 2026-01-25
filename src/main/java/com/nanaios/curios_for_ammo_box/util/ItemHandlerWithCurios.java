package com.nanaios.curios_for_ammo_box.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import org.jetbrains.annotations.NotNull;

public class ItemHandlerWithCurios implements IItemHandler {
    final Player player;
    final CuriosHandler handler;
    public ItemHandlerWithCurios(IItemHandler itemHandler, Player player) {
        this.player = player;
        this.handler = new CuriosHandler((ICuriosHandlerSourceMarker) itemHandler,player);
    }

    @Override
    public int getSlots() {
        return handler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return handler.getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return handler.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return false;
    }

    public static IItemHandler create(IItemHandler itemHandler) {
        if(itemHandler instanceof  PlayerInvWrapper playerWap) {
            IItemHandlerModifiable[] handlers = playerWap.itemHandler;
            if(handlers[0] instanceof PlayerMainInvWrapper mainWap) {
                return new ItemHandlerWithCurios(itemHandler, mainWap.getInventoryPlayer().player);
            }
        }
        return itemHandler;
    }
}

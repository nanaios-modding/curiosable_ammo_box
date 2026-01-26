package com.nanaios.curios_for_ammo_box.util;

import com.nanaios.curios_for_ammo_box.mixin.common.MixinCombinedInvWrapper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemHandlerWithCurios implements IItemHandler {
    final Player player;
    final IItemHandler source;
    final ICuriosItemHandler curiosHandler;

    public ItemHandlerWithCurios(IItemHandler source, Player player) {
        this.player = player;
        this.source = source;
        this.curiosHandler = CuriosApi.getCuriosInventory(player).orElse(new EmptyCuriosItemHandler());
    }

    public List<IItemHandler> getHandlers() {
        List<IItemHandler> handlers = new ArrayList<>();
        handlers.add(source);

        for(Map.Entry<String, ICurioStacksHandler> curiosEntry: curiosHandler.getCurios().entrySet()){
            handlers.add(curiosEntry.getValue().getStacks());
        }

        return handlers;
    }

    @Override
    public int getSlots() {
        return source.getSlots() + curiosHandler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        List<IItemHandler> handlers = getHandlers();
        for(IItemHandler handler: handlers) {
            if(slot < handler.getSlots()) {
                return handler.getStackInSlot(slot);
            } else {
                slot -= handler.getSlots();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        List<IItemHandler> handlers = getHandlers();
        for(IItemHandler handler: handlers) {
            if(slot < handler.getSlots()) {
                return handler.insertItem(slot, stack, simulate);
            } else {
                slot -= handler.getSlots();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        List<IItemHandler> handlers = getHandlers();
        for(IItemHandler handler: handlers) {
            if(slot < handler.getSlots()) {
                return handler.extractItem(slot, amount, simulate);
            } else {
                slot -= handler.getSlots();
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        List<IItemHandler> handlers = getHandlers();
        for(IItemHandler handler: handlers) {
            if(slot < handler.getSlots()) {
                return handler.getSlotLimit(slot);
            } else {
                slot -= handler.getSlots();
            }
        }
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        List<IItemHandler> handlers = getHandlers();
        for(IItemHandler handler: handlers) {
            if(slot < handler.getSlots()) {
                return handler.isItemValid(slot, stack);
            } else {
                slot -= handler.getSlots();
            }
        }
        return false;
    }

    public static IItemHandler create(IItemHandler itemHandler) {
        if(itemHandler instanceof PlayerInvWrapper playerWap) {
            IItemHandlerModifiable[] handlers = ((MixinCombinedInvWrapper)playerWap).mixinCFAB$getItemHandler();
            if(handlers[0] instanceof PlayerMainInvWrapper mainWap) {
                return new ItemHandlerWithCurios(itemHandler, mainWap.getInventoryPlayer().player);
            }
        }
        return itemHandler;
    }
}

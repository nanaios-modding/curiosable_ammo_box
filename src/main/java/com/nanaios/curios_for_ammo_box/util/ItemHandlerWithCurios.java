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
import java.util.Collection;

public class ItemHandlerWithCurios implements IItemHandler {
    private final IItemHandler sourceHandler;
    private final ICuriosItemHandler curiosHandler;

    public ItemHandlerWithCurios(IItemHandler source, Player player) {
        this.sourceHandler = source;
        this.curiosHandler = CuriosApi.getCuriosInventory(player).orElse(EmptyCuriosItemHandler.INSTANCE);
    }

    public List<IItemHandler> getHandlers() {
        Collection<ICurioStacksHandler> curiosEntries = curiosHandler.getCurios().values();
        List<IItemHandler> handlers = new ArrayList<>(1 + curiosEntries.size());
        handlers.add(sourceHandler);

        for (ICurioStacksHandler stacksHandler : curiosEntries) {
            handlers.add(stacksHandler.getStacks());
        }

        return handlers;
    }

    private HandlerSlot resolveSlot(int slot) {
        int remaining = slot;
        for (IItemHandler handler : getHandlers()) {
            int handlerSlots = handler.getSlots();
            if (remaining < handlerSlots) {
                return new HandlerSlot(handler, remaining);
            }
            remaining -= handlerSlots;
        }
        return null;
    }

    private record HandlerSlot(IItemHandler handler, int localSlot) {
    }

    @Override
    public int getSlots() {
        return sourceHandler.getSlots() + curiosHandler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        HandlerSlot handlerSlot = resolveSlot(slot);
        return handlerSlot != null
                ? handlerSlot.handler.getStackInSlot(handlerSlot.localSlot)
                : ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        HandlerSlot handlerSlot = resolveSlot(slot);
        return handlerSlot != null
                ? handlerSlot.handler.insertItem(handlerSlot.localSlot, stack, simulate)
                : ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        HandlerSlot handlerSlot = resolveSlot(slot);
        return handlerSlot != null
                ? handlerSlot.handler.extractItem(handlerSlot.localSlot, amount, simulate)
                : ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        HandlerSlot handlerSlot = resolveSlot(slot);
        return handlerSlot != null
                ? handlerSlot.handler.getSlotLimit(handlerSlot.localSlot)
                : 0;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        HandlerSlot handlerSlot = resolveSlot(slot);
        return handlerSlot != null && handlerSlot.handler.isItemValid(handlerSlot.localSlot, stack);
    }

    public static IItemHandler create(IItemHandler itemHandler) {
        if (itemHandler instanceof PlayerInvWrapper playerWrapper) {
            IItemHandlerModifiable[] handlers = ((MixinCombinedInvWrapper) playerWrapper).curiosForAmmoBox$getItemHandlers();
            if (handlers.length > 0 && handlers[0] instanceof PlayerMainInvWrapper mainWrapper) {
                return new ItemHandlerWithCurios(itemHandler, mainWrapper.getInventoryPlayer().player);
            }
        }
        return itemHandler;
    }
}

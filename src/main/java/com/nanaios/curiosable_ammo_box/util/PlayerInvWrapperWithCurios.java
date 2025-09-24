package com.nanaios.curiosable_ammo_box.util;

import com.nanaios.curiosable_ammo_box.mixin.MixinCuriosableAmmoBoxCombinedInvWrapper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import org.jetbrains.annotations.NotNull;

public class PlayerInvWrapperWithCurios extends PlayerInvWrapper {
    private InventoryWithCurios inv;
    public PlayerInvWrapperWithCurios(Inventory inventory) {
        super(inventory);
        inv = new InventoryWithCurios(inventory);
    }

    @Override
    public int getSlots() {
        return inv.getContainerSize();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return inv.getItem(slot);
    }

    public static IItemHandler create(IItemHandler itemHandler) {
        if(itemHandler instanceof  PlayerInvWrapper playerWap) {
            IItemHandlerModifiable[] handlers = ((MixinCuriosableAmmoBoxCombinedInvWrapper)playerWap).mixinCuriosableAmmoBoxItemHandler();
            if(handlers[0] instanceof PlayerMainInvWrapper mainWap) {
                return new PlayerInvWrapperWithCurios(mainWap.getInventoryPlayer());
            }
        }
        if(itemHandler instanceof Inventory inventory) {
            return new PlayerInvWrapperWithCurios(inventory);
        }
        return  itemHandler;
    }
}

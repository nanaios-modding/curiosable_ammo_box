package com.nanaios.curiosable_ammo_box.util;

import com.nanaios.curiosable_ammo_box.mixin.MixinCuriosableAmmoBoxCombinedInvWrapper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;

import java.util.Arrays;

public class InvWrapper extends PlayerInvWrapper {
    Inventory inv;
    public InvWrapper(Inventory inv) {
        super(inv);
        this.inv = inv;
    }





    public static IItemHandler create(IItemHandler itemHandler) {
        if(itemHandler instanceof  PlayerInvWrapper playerWap) {
            IItemHandlerModifiable[] handlers = ((MixinCuriosableAmmoBoxCombinedInvWrapper)playerWap).mixinCuriosableAmmoBoxItemHandler();
            if(handlers[0] instanceof PlayerMainInvWrapper mainWap) {
                return new InvWrapper(mainWap.getInventoryPlayer());
            }
        }
        return  itemHandler;
    }
}

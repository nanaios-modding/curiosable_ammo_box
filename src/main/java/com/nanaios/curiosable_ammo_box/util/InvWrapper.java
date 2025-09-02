package com.nanaios.curiosable_ammo_box.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.wrapper.PlayerInvWrapper;

import java.util.Arrays;

public class InvWrapper extends PlayerInvWrapper {
    public InvWrapper(Inventory inv) {
        super(inv);
    }
    static InvWrapper create(PlayerInvWrapper wrp) {
    }
}

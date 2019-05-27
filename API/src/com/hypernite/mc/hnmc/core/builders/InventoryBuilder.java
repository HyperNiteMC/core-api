package com.hypernite.mc.hnmc.core.builders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder {
    public static final int ONE_ROW = 9;
    public static final int CENTER = 5;
    private Inventory inventory;


    public InventoryBuilder(int size, String title) {
        inventory = Bukkit.createInventory(null, size, title);
    }

    /**
     * @param item 物品
     * @return this
     */
    public InventoryBuilder item(ItemStack item) {
        inventory.addItem(item);
        return this;
    }

    /**
     * @param slot 槽位
     * @param item 物品
     * @return this
     */
    public InventoryBuilder item(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

    /**
     * @param row  行數
     * @param slot 欄位
     * @param item 物品
     * @return this
     */
    //start from row 1, slot 1
    public InventoryBuilder item(int row, int slot, ItemStack item) {
        inventory.setItem((row - 1) * ONE_ROW + slot - 1, item);
        return this;
    }


    public Inventory build() {
        return inventory;
    }
}

// 如何使用
/*class TestInventoryBuilder {
    public void useInvBuilder() {
        InventoryBuilder inv = new InventoryBuilder(54, "title");
        inv.item(1, InventoryBuilder.CENTER, new ItemStack(Material.ALLIUM))
                .item(27, new ItemStack(Material.ELYTRA))
                .item(new ItemStack(Material.ACTIVATOR_RAIL))
                .build();
    }
}*/
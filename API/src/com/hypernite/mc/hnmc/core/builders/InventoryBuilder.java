package com.hypernite.mc.hnmc.core.builders;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder {
    public static final int ONE_ROW = 9;
    public static final int CENTER = 5;
    private Inventory inventory;
    private int row;


    public InventoryBuilder(int row, String title) {
        if (row > 6) row = 6;
        this.row = row;
        String colorTitle = ChatColor.translateAlternateColorCodes('&', title);
        inventory = Bukkit.createInventory(null, ONE_ROW * row, colorTitle);
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

    /**
     * 設置中央位置
     * <br>
     * 若果你是雙數行，則無法找出中央位置
     *
     * @param item 物品
     * @return this
     */
    public InventoryBuilder center(ItemStack item) {
        int realRow = row % 2 == 0 ? row / 2 : row / 2 + 1;
        this.item(realRow, CENTER, item);
        return this;
    }

    /**
     * 設置環狀圍繞
     *
     * @param item 物品
     * @return this
     * @throws IllegalStateException 背包界面少於三行
     */
    public InventoryBuilder ring(ItemStack item) {
        if (this.row < 3) {
            throw new IllegalStateException("若要使用物品環繞，你的界面最少要擁有三行。");
        }
        for (int i = 1; i < this.row + 1; i++) {
            if (i == 1 || i == this.row) {
                this.fillRow(i, item);
            } else {
                this.item(i, 1, item);
                this.item(i, 9, item);
            }
        }
        return this;
    }

    /**
     * 填滿一整行
     *
     * @param row  行數
     * @param item 物品
     * @return this
     */
    public InventoryBuilder fillRow(int row, ItemStack item) {
        for (int i = 1; i < ONE_ROW + 1; i++) {
            this.item(row, i, item);
        }
        return this;
    }


    public Inventory build() {
        return inventory;
    }
}
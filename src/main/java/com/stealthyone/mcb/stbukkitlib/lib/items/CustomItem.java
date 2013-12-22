/*
 * StBukkitLib - Set of useful Bukkit-related classes
 * Copyright (C) 2013 Stealth2800 <stealth2800@stealthyone.com>
 * Website: <http://google.com/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.stealthyone.mcb.stbukkitlib.lib.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomItem extends ItemStack {

    public CustomItem(ItemStack itemStack) {
        super(itemStack);
    }

    public CustomItem(Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    public CustomItem(Material type, int amount) {
        super(type, amount);
    }

    public CustomItem(Material type) {
        this(type, 1);
    }

    public void setName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(name);
        setItemMeta(meta);
    }

    public String getName() {
        return getItemMeta().getDisplayName();
    }

    public void setLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        meta.setLore(lore);
        setItemMeta(meta);
    }

    public void addLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        List<String> currentLore;
        if (meta.hasLore()) {
            currentLore = meta.getLore();
            currentLore.addAll(lore);
            setItemMeta(meta);
        } else {
            setLore(lore);
        }
    }

    public List<String> getLore() {
        return getItemMeta().getLore();
    }

}
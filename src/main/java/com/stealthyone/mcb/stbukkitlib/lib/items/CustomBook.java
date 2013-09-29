package com.stealthyone.mcb.stbukkitlib.lib.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class CustomBook extends CustomItem {

    public CustomBook() {
        super(Material.WRITTEN_BOOK);
    }

    public CustomBook(ItemStack stack) {
        super(stack);
    }

    public List<String> getPages() {
        BookMeta meta = (BookMeta) getItemMeta();

        return meta.getPages();
    }

    public void setPages(List<String> pages) {
        BookMeta meta = (BookMeta) getItemMeta();
        meta.setPages(pages);
        setItemMeta(meta);
    }

    public void addPages(String... pages) {
        BookMeta meta = (BookMeta) getItemMeta();
        meta.addPage(pages);
        setItemMeta(meta);
    }

    public String getAuthor() {
        return ((BookMeta) getItemMeta()).getAuthor();
    }

    public void setAuthor(String name) {
        BookMeta meta = (BookMeta) getItemMeta();
        meta.setAuthor(name);
        setItemMeta(meta);
    }

}
package com.stealthyone.mcb.stbukkitlib.lib.items;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CustomBook extends CustomItem {

	public CustomBook(ItemStack stack) throws IllegalArgumentException {
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

}
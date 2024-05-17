package net.masonio.mosquitos;

import net.fabricmc.api.ModInitializer;

import net.masonio.mosquitos.enchantment.LeechEnchantment;
import net.masonio.mosquitos.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mosquitos implements ModInitializer {
	public static final String MOD_ID = "mosquitos";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Enchantment LEECH = new LeechEnchantment();

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		Registry.register(Registries.ENCHANTMENT, new Identifier("mosquitos", "leech"), LEECH);
	}
}
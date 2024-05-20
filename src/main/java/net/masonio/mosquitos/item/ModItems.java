package net.masonio.mosquitos.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.masonio.mosquitos.Mosquitos;
import net.masonio.mosquitos.item.custom.MosquitoSackItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item REDMOSQUITOSAC = registerItem("red_mosquito_sack", new MosquitoSackItem(new Item.Settings().maxDamage(21)));
    public static final Item YELLOWMOSQUITOSAC = registerItem("yellow_mosquito_sack", new MosquitoSackItem(new Item.Settings().maxDamage(21)));

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries){
        entries.addAfter(Items.TOTEM_OF_UNDYING, REDMOSQUITOSAC);
        entries.addAfter(REDMOSQUITOSAC, YELLOWMOSQUITOSAC);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Mosquitos.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Mosquitos.LOGGER.info("Registering Mod Items for " + Mosquitos.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTabItemGroup);
    }
}

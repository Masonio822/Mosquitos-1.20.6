package net.masonio.mosquitos.util;

import net.masonio.mosquitos.Mosquitos;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModTags {
    public static class Items {
        public static final TagKey<Item> SACK_ENCHANTABLE = createTag("sack_enchantable");

        private static TagKey<Item> createTag(String id) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Mosquitos.MOD_ID, id));
        }
    }
}

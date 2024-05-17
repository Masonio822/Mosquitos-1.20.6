package net.masonio.mosquitos.enchantment;

import net.masonio.mosquitos.util.ModTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class LeechEnchantment extends Enchantment {
    public LeechEnchantment() {
        super(Enchantment.properties(
                ModTags.Items.SACK_ENCHANTABLE,
                2,
                1,
                Enchantment.constantCost(15),
                Enchantment.constantCost(65),
                4,
                EquipmentSlot.MAINHAND));
        System.out.println(ModTags.Items.SACK_ENCHANTABLE);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}

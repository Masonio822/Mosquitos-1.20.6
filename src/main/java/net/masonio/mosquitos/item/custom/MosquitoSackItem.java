package net.masonio.mosquitos.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MosquitoSackItem extends Item {
    public MosquitoSackItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!(user.getHealth() == user.getMaxHealth())) {
            int healthToHeal = (int) (user.getMaxHealth() - user.getHealth());
            ItemStack itemStack = user.getStackInHand(hand);
            if (healthToHeal < ((itemStack.getMaxDamage() - itemStack.getDamage()))) {
                itemStack.damage(healthToHeal, user, LivingEntity.getSlotForHand(hand));
                user.heal(healthToHeal);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}

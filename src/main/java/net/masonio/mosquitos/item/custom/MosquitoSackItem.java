package net.masonio.mosquitos.item.custom;

import net.masonio.mosquitos.item.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.masonio.mosquitos.Mosquitos.LEECH;

public class MosquitoSackItem extends Item {
    public MosquitoSackItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && !(user.getEquippedStack(EquipmentSlot.MAINHAND).getDamage() == user.getEquippedStack(EquipmentSlot.MAINHAND).getMaxDamage() - 1)) {
            ItemStack itemStack = user.getStackInHand(hand);
            int durability = itemStack.getMaxDamage() - itemStack.getDamage();
            int healthToHeal;
            System.out.println(getMaxUseTime(itemStack));
            if (!(user.getHealth() == user.getMaxHealth())) {
                healthToHeal = (int) (user.getMaxHealth() - user.getHealth());
                if (healthToHeal > durability && !(durability == 1)) {
                    healthToHeal = durability - 1;
                }
                for (int i = healthToHeal; i > 0; i--) {
                    user.setCurrentHand(hand);
                    user.heal(1);
                    itemStack.damage(1, user, LivingEntity.getSlotForHand(hand));
                    try {
                        wait(itemStack.getMaxUseTime() / 20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return TypedActionResult.success(user.getStackInHand(hand));
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public boolean postHit(ItemStack itemStack, LivingEntity target, LivingEntity attacker){
        if (!(EnchantmentHelper.getLevel(LEECH, itemStack) == 0)) {
            if (target.isDead() && !target.isPlayer()) {
                itemStack.damage(-1, target, EquipmentSlot.MAINHAND);
            }
        }
        return false;
    }

    @Override
    public int getMaxUseTime(ItemStack itemStack){
        if (itemStack.isOf(ModItems.YELLOWMOSQUITOSAC)) {
            return 20; //1 Second
        }
        return 40; //2 Seconds
    }
}

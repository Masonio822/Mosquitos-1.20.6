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
    int useTickTimer = 0;
    public MosquitoSackItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && canUseSack(user)) {
            useTickTimer = 0;
            user.setCurrentHand(hand);
            return TypedActionResult.consume(user.getStackInHand(hand));
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
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (canUseSack(user) && !world.isClient()) {
            if (useTickTimer % (stack.getMaxUseTime() / 20) == 0) {
                user.heal(1);
                stack.damage(1, user, EquipmentSlot.MAINHAND);
            }
            useTickTimer++;
        }
    }

    @Override
    public int getMaxUseTime(ItemStack itemStack){
        if (itemStack.isOf(ModItems.YELLOWMOSQUITOSAC)) {
            return 20; //1 Second
        }
        return 40; //2 Seconds
    }

    public boolean canUseSack(LivingEntity user) {
        ItemStack stack = user.getEquippedStack(EquipmentSlot.MAINHAND);
        return !(stack.getDamage() == stack.getMaxDamage() - 1) && !(user.getMaxHealth() == user.getHealth());
    }
}

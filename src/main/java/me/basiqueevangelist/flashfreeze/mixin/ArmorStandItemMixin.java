package me.basiqueevangelist.flashfreeze.mixin;

import me.basiqueevangelist.flashfreeze.FakeArmorStandEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorStandItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(ArmorStandItem.class)
public class ArmorStandItemMixin {
    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityType;create(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/nbt/NbtCompound;Ljava/util/function/Consumer;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/SpawnReason;ZZ)Lnet/minecraft/entity/Entity;"))
    private Entity makeEntity(EntityType<?> entityType, ServerWorld world, NbtCompound itemNbt, Consumer afterConsumer, BlockPos pos, SpawnReason spawnReason, boolean alignPosition, boolean invertY) {
        if (!itemNbt.isEmpty() && itemNbt.contains("OriginalEntityData", NbtElement.COMPOUND_TYPE)) {
            Entity e = new FakeArmorStandEntity(world, itemNbt.getCompound("OriginalEntityData").copy());
            e.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            return e;
        }

        return entityType.create(world, itemNbt, afterConsumer, pos, spawnReason, alignPosition, invertY);
    }

}

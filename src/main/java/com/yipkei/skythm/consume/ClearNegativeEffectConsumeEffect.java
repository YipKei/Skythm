/*
package com.yipkei.skythm.consume;

import com.mojang.serialization.MapCodec;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.world.World;


public record ClearNegativeEffectConsumeEffect() implements ConsumeEffect {
    public static final ClearNegativeEffectConsumeEffect INSTANCE = new ClearNegativeEffectConsumeEffect();
    public static final MapCodec<ClearNegativeEffectConsumeEffect> CODEC = MapCodec.unit(INSTANCE);
    public static final PacketCodec<RegistryByteBuf, ClearNegativeEffectConsumeEffect> PACKET_CODEC = PacketCodec.unit(INSTANCE);

    @Override
    public ConsumeEffect.Type<ClearNegativeEffectConsumeEffect> getType() {
        return ConsumeEffect.Type.CLEAR_NEGATIVE_EFFECTS;
    }

    @Override
    public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
        user.getStatusEffects();
        return false;
    }


}

 */

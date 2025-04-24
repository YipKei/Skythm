/*
package com.yipkei.skythm.consume;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public interface ModConsumeEffect {
    Codec<ModConsumeEffect> CODEC = Registries.CONSUME_EFFECT_TYPE.getCodec().dispatch(ModConsumeEffect::getType, ModConsumeEffect.Type::codec);
    PacketCodec<RegistryByteBuf, ModConsumeEffect> PACKET_CODEC = PacketCodecs.registryValue(RegistryKeys.CONSUME_EFFECT_TYPE).dispatch(ModConsumeEffect::getType, ModConsumeEffect.Type::streamCodec);

    ModConsumeEffect.Type<? extends ModConsumeEffect> getType();

    boolean onConsume(World world, ItemStack stack, LivingEntity user);

    public record Type<T extends  ModConsumeEffect>(MapCodec<T> codec, PacketCodec<RegistryByteBuf, T> streamCodec) {
        public static final ModConsumeEffect.Type<ApplyEffectsConsumeEffect> ClEAR_NEGATIVE_EFFECTS = register("clear_negative_effects", ClearNegativeEffectConsumeEffect.CODEC, ClearAllEffectsConsumeEffect.PACKET_CODEC);
    }

    private static <T extends ModConsumeEffect> ModConsumeEffect.Type<T> register(String id, MapCodec<T> codec, PacketCodec<RegistryByteBuf, T> packetCodec) {
        return Registry.register(Registries.CONSUME_EFFECT_TYPE, id, new ModConsumeEffect.Type<>(codec, packetCodec));
    }
}
*/
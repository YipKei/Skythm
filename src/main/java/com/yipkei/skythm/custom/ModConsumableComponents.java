package com.yipkei.skythm.custom;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

public class ModConsumableComponents {


    public static final ConsumableComponent DRIED_PORK = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 0)))
            .build();

    public static final ConsumableComponent DRIED_BEEF = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 0)))
            .build();

    public static final ConsumableComponent DRIED_APPLE = food()
            .consumeSeconds(0.5f)
            .build();

    public static final ConsumableComponent DRIED_VEGETABLES = food()
            .consumeSeconds(0.5f)
            .build();

    public static final ConsumableComponent MILK_BOTTLE = drink()
            .consumeSeconds(0.8f)
            .consumeEffect(ClearAllEffectsConsumeEffect.INSTANCE)
            .build();

    public static final ConsumableComponent COCOA_DRINK = drink()
            .consumeSeconds(0.8f)
            .consumeEffect(ClearAllEffectsConsumeEffect.INSTANCE)
            .build();

    public static ConsumableComponent.Builder food() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.EAT).sound(SoundEvents.ENTITY_GENERIC_EAT).consumeParticles(true);
    }

    public static ConsumableComponent.Builder drink() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.DRINK).sound(SoundEvents.ENTITY_GENERIC_DRINK).consumeParticles(false);
    }
}

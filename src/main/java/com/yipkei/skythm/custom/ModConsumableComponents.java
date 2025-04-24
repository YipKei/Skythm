package com.yipkei.skythm.custom;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

import java.util.List;

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
            .consumeSeconds(0.4f)
            .build();

    public static final ConsumableComponent DRIED_VEGETABLES = food()
            .consumeSeconds(0.4f)
            .build();

    public static final ConsumableComponent MILK_BOTTLE = drink()
            .consumeSeconds(0.8f)
            .consumeEffect(ClearAllEffectsConsumeEffect.INSTANCE)
            .build();

    public static final ConsumableComponent COCOA_DRINK = drink()
            .consumeSeconds(0.8f)
            .consumeEffect(ClearAllEffectsConsumeEffect.INSTANCE)
            .build();

    public static final ConsumableComponent BULLET_DRINK = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 3)))
            .build();

    public static final ConsumableComponent KELP_SOUP = food()
            .consumeSeconds(1.0f)
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 200)))
            .build();

    public static final ConsumableComponent ROAST_MUSHROOM = food()
            .consumeSeconds(0.4f)
            .build();

    public static final ConsumableComponent JUICE = drink()
            .consumeSeconds(0.8f)
            .build();

    public static final ConsumableComponent MONSTER_BLACK = drink()
            .consumeSeconds(6.0f)
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    List.of(
                            new StatusEffectInstance(StatusEffects.DARKNESS, 300),
                            new StatusEffectInstance(StatusEffects.STRENGTH, 300)
                    )
            ))
            .build();

    public static final ConsumableComponent MONSTER_WHITE = drink()
            .consumeSeconds(6.0f)
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    List.of(
                            new StatusEffectInstance(StatusEffects.HUNGER, 300),
                            new StatusEffectInstance(StatusEffects.HASTE, 300, 1)
                    )
            ))
            .build();

    public static final ConsumableComponent MONSTER_PINK = drink()
            .consumeSeconds(6.0f)
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    List.of(
                            new StatusEffectInstance(StatusEffects.WEAKNESS, 300),
                            new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 300),
                            new StatusEffectInstance(StatusEffects.SLOWNESS, 300),
                            new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 300),
                            new StatusEffectInstance(StatusEffects.RESISTANCE, 300)
                    )))
            .build();

    public static ConsumableComponent.Builder food() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.EAT).sound(SoundEvents.ENTITY_GENERIC_EAT).consumeParticles(true);
    }

    public static ConsumableComponent.Builder drink() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.DRINK).sound(SoundEvents.ENTITY_GENERIC_DRINK).consumeParticles(false);
    }
}

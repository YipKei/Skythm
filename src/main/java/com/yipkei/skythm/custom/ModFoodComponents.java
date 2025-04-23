package com.yipkei.skythm.custom;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent DRIED_ROTTEN_FLESH = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.1f)
            .build();

    public static final FoodComponent DRIED_PORK = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.5f)
            .build();

    public static final FoodComponent DRIED_BEEF = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.5f)
            .build();

    public static final FoodComponent DRIED_FISH = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.5f)
            .build();

    public static final FoodComponent DRIED_APPLE = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.2f)
            .build();

    public static final FoodComponent VEGETABLE_ASSORTED = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.4f)
            .build();

    public static final FoodComponent DRIED_VEGETABLES = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.2f)
            .build();
}

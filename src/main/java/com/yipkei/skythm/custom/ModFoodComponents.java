package com.yipkei.skythm.custom;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent DRIED_ROTTEN_FLESH = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.1f)
            .build(); // 回复量3.6，腐肉的期望值为4.2

    public static final FoodComponent DRIED_PORK = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.3f)
            .build(); // 回复量9.6，生猪排位为4.8，熟猪排为20.8

    public static final FoodComponent DRIED_BEEF = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.3f)
            .build(); // 回复量9.6，生牛肉为4.8，熟牛肉为20.8

    public static final FoodComponent DRIED_FISH = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .build(); // 回复量6.4，生鲑鱼/生鳕鱼回复量2.4，热带鱼回复量1.2，熟鱼回复量11/15.6

    public static final FoodComponent DRIED_APPLE = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build(); // 回复量3.2，苹果回复量6.4

    public static final FoodComponent VEGETABLE_STEW = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.4f)
            .build(); // 回复量10.8，胡萝卜6.6，土豆1.6，南瓜不能吃

    public static final FoodComponent DRIED_VEGETABLES = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.8f)
            .alwaysEdible()
            .build(); // 回复量2.6，八份回复20.8

    public static final FoodComponent BULLET_DRINK = new FoodComponent.Builder()
            .nutrition(0)
            .saturationModifier(0.0f)
            .alwaysEdible()
            .build();

    public static final FoodComponent KELP_SOUP = new FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.2f)
            .build(); // 回复量7

    public static final FoodComponent ROAST_MUSHROOM = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.2f)
            .alwaysEdible()
            .build(); // 回复量2.8，蘑菇不能吃

    public static final FoodComponent JUICE = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .build(); // 回复量7.2，西瓜汁

    public static final FoodComponent MONSTER = new FoodComponent.Builder()
            .nutrition(0)
            .saturationModifier(0.0f)
            .alwaysEdible()
            .build();
}

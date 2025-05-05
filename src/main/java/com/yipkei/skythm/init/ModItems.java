package com.yipkei.skythm.init;

import com.yipkei.skythm.Skythm;
import com.yipkei.skythm.custom.ModConsumableComponents;
import com.yipkei.skythm.custom.ModFoodComponents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // 食物
    public static final Item DRIED_ROTTEN_FLESH = registerItems("dried_rotten_flesh", new Item(new Item.Settings().food(ModFoodComponents.DRIED_ROTTEN_FLESH)));
    public static final Item DRIED_PORK = registerItems("dried_pork", new Item(new Item.Settings().food(ModFoodComponents.DRIED_PORK, ModConsumableComponents.DRIED_PORK)));
    public static final Item DRIED_BEEF = registerItems("dried_beef", new Item(new Item.Settings().food(ModFoodComponents.DRIED_BEEF, ModConsumableComponents.DRIED_BEEF)));
    public static final Item DRIED_FISH = registerItems("dried_fish", new Item(new Item.Settings().food(ModFoodComponents.DRIED_FISH)));
    public static final Item DRIED_APPLE = registerItems("dried_apple", new Item(new Item.Settings().food(ModFoodComponents.DRIED_APPLE, ModConsumableComponents.DRIED_APPLE)));
    public static final Item VEGETABLE_STEW = registerItems("vegetable_assorted", new Item(new Item.Settings().maxCount(1).food(ModFoodComponents.VEGETABLE_STEW).useRemainder(Items.BOWL)));
    public static final Item DRIED_VEGETABLES = registerItems("dried_vegetables", new Item(new Item.Settings().food(ModFoodComponents.DRIED_VEGETABLES, ModConsumableComponents.DRIED_VEGETABLES)));
    public static final Item MILK_BOTTLE = registerItems("milk_bottle", new Item(new Item.Settings().maxCount(6).component(DataComponentTypes.CONSUMABLE, ModConsumableComponents.MILK_BOTTLE).useRemainder(Items.GLASS_BOTTLE)));
    public static final Item COCOA_DRINK = registerItems("cocoa_drink", new Item(new Item.Settings().maxCount(6).component(DataComponentTypes.CONSUMABLE, ModConsumableComponents.COCOA_DRINK).useRemainder(Items.GLASS_BOTTLE)));
    public static final Item BULLET_SOUP = registerItems("bullet_drink", new Item(new Item.Settings().maxCount(1).food(ModFoodComponents.BULLET_DRINK, ModConsumableComponents.BULLET_DRINK).useRemainder(Items.BOWL)));
    public static final Item KELP_SOUP = registerItems("kelp_soup",new Item(new Item.Settings().maxCount(1).food(ModFoodComponents.KELP_SOUP, ModConsumableComponents.KELP_SOUP)));
    public static final Item ROAST_MUSHROOM = registerItems("roast_mushroom", new Item(new Item.Settings().food(ModFoodComponents.ROAST_MUSHROOM, ModConsumableComponents.ROAST_MUSHROOM)));
    public static final Item MELON_JUICE = registerItems("melon_juice", new Item(new Item.Settings().maxCount(6).food(ModFoodComponents.JUICE, ModConsumableComponents.JUICE)));
    public static final Item SUGARCANE_JUICE = registerItems("sugarcane_juice", new Item(new Item.Settings().maxCount(6).food(ModFoodComponents.JUICE,ModConsumableComponents.JUICE)));
    public static final Item VEGETABLE_JUICE = registerItems("vegetable_juice", new Item(new Item.Settings().maxCount(6).food(ModFoodComponents.JUICE, ModConsumableComponents.JUICE)));
    public static final Item MONSTER_BLACK = registerItems("monster_black", new Item(new Item.Settings().maxCount(6).food(ModFoodComponents.MONSTER, ModConsumableComponents.MONSTER_BLACK)));
    public static final Item MONSTER_WHITE = registerItems("monster_white", new Item(new Item.Settings().maxCount(6).food(ModFoodComponents.MONSTER, ModConsumableComponents.MONSTER_WHITE)));
    public static final Item MONSTER_PINK = registerItems("monster_pink", new Item(new Item.Settings().maxCount(6).food(ModFoodComponents.MONSTER, ModConsumableComponents.MONSTER_PINK)));

    // 食物半成品
    public static final Item CAKE_BASE = registerItems("cake_base", new Item(new Item.Settings().maxCount(1)));
    public static final Item RAW_COOKIE = register("raw_cake");
    public static final Item RAW_BREAD = register("raw_bread");
    public static final Item RAW_PIE = registerItems("raw_pie", new Item(new Item.Settings().maxCount(1)));
    public static final Item RAW_VEGETABLE_STEW = registerItems("raw_vegetable_stew", new Item(new Item.Settings().maxCount(1)));
    public static final Item RAW_RABBIT_STEW = registerItems("raw_rabbit_stew", new Item(new Item.Settings().maxCount(1)));
    public static final Item RAW_MUSHROOM_STEW = registerItems("raw_mushroom_stew", new Item(new Item.Settings().maxCount(1)));
    public static final Item RAW_BEETROOT_STEW = registerItems("raw_beetroot_stew", new Item(new Item.Settings().maxCount(1)));

    // 作物
    public static final Item NORI = registerItems("nori", new Item(new Item.Settings()));

    // 工具类


    // 道具类
    public static final Item MANURE = register("manure", BoneMealItem::new);


    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){}
    private static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Skythm.MOD_ID,name),item);
    }
    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIG);
    }

    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Skythm.MOD_ID, id));
    }

    public static Item register(String id, Function<Item.Settings, Item> factory) {
        return register(keyOf(id), factory, new Item.Settings());
    }

    public static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return register(keyOf(id), factory, settings);
    }

    public static Item register(String id, Item.Settings settings) {
        return register(keyOf(id), Item::new, settings);
    }

    public static Item register(String id) {
        return register(keyOf(id), Item::new, new Item.Settings());
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return register(key, factory, new Item.Settings());
    }


}

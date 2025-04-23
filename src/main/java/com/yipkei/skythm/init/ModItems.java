package com.yipkei.skythm.init;

import com.yipkei.skythm.Skythm;
import com.yipkei.skythm.custom.ModConsumableComponents;
import com.yipkei.skythm.custom.ModFoodComponents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // 食物
    public static final Item DRIED_ROTTEN_FLESH = registerItems("dried_rotten_flesh", new Item(new Item.Settings().food(ModFoodComponents.DRIED_ROTTEN_FLESH)));
    public static final Item DRIED_PORK = registerItems("dried_pork", new Item(new Item.Settings().food(ModFoodComponents.DRIED_PORK, ModConsumableComponents.DRIED_PORK)));
    public static final Item DRIED_BEEF = registerItems("dried_beef", new Item(new Item.Settings().food(ModFoodComponents.DRIED_BEEF, ModConsumableComponents.DRIED_BEEF)));
    public static final Item DRIED_FISH = registerItems("dried_fish", new Item(new Item.Settings().food(ModFoodComponents.DRIED_FISH)));
    public static final Item DRIED_APPLE = registerItems("dried_apple", new Item(new Item.Settings().food(ModFoodComponents.DRIED_APPLE, ModConsumableComponents.DRIED_APPLE)));
    public static final Item VEGETABLE_ASSORTED = registerItems("vegetable_assorted", new Item(new Item.Settings().maxCount(1).food(ModFoodComponents.VEGETABLE_ASSORTED).useRemainder(Items.BOWL)));
    public static final Item DRIED_VEGETABLES = registerItems("dried_vegetables", new Item(new Item.Settings().food(ModFoodComponents.DRIED_VEGETABLES, ModConsumableComponents.DRIED_VEGETABLES)));
    public static final Item MILK_BOTTLE = registerItems("milk_bottle", new Item(new Item.Settings().maxCount(16).component(DataComponentTypes.CONSUMABLE, ModConsumableComponents.MILK_BOTTLE).useRemainder(Items.GLASS_BOTTLE)));
    public static final Item COCOA_DRINK = registerItems("cocoa_drink", new Item(new Item.Settings().maxCount(16).component(DataComponentTypes.CONSUMABLE, ModConsumableComponents.COCOA_DRINK).useRemainder(Items.GLASS_BOTTLE)));

    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){}
    private static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Skythm.MOD_ID,name),item);
    }
    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIG);
    }
}

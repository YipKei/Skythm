package com.yipkei.skythm.init;

import com.yipkei.skythm.Skythm;
import com.yipkei.skythm.block.SteelTrussBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block STEEL_TRUSS = register("steek_truss", SteelTrussBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).noCollision().sounds(BlockSoundGroup.IRON).dynamicBounds().allowsSpawning(Blocks::never).pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never));

    public static final Block WHITE_FERROCONCRETE = register("white_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block ORANGE_FERROCONCRETE = register("orange_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block MAGENTA_FERROCONCRETE = register("magenta_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block LIGHT_BLUE_FERROCONCRETE = register("light_blue_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block YELLOW_FERROCONCRETE = register("yellow_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block LIME_FERROCONCRETE = register("lime_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block PINK_FERROCONCRETE = register("pink_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block GRAY_FERROCONCRETE = register("gray_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block LIGHT_GRAY_FERROCONCRETE = register("light_gray_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block CYAN_FERROCONCRETE = register("cyan_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block PURPLE_FERROCONCRETE = register("purple_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block BLUE_FERROCONCRETE = register("blue_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block BROWN_FERROCONCRETE = register("brown_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block GREEN_FERROCONCRETE = register("green_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block RED_FERROCONCRETE = register("red_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block BLACK_FERROCONCRETE = register("black_ferroconcrete", AbstractBlock.Settings.create().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(15.0f, 12.0f).pistonBehavior(PistonBehavior.BLOCK));

    public static final Block RAMMED_EARTH = register("rammed_earth", AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0f, 4.0f).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block TUNGSTEN_STEEL_BLOCK = register("tungsten_steel_block", AbstractBlock.Settings.create().mapColor(DyeColor.BROWN).sounds(BlockSoundGroup.NETHERITE).requiresTool().strength(8.0f, 16.0f));

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Skythm.MOD_ID, id));
    }

    private static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Blocks.register(keyOf(id), factory, settings);
    }

    private static Block register(String id, AbstractBlock.Settings settings){
        return register(id, Block::new, settings);
    }
}

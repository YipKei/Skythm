package com.yipkei.skythm.init;

import com.yipkei.skythm.Skythm;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> FERROCONCRETE = createTag("ferroconcrete");

        private static TagKey<Block> createTag(String name){

            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Skythm.MOD_ID, name));
        }
    }
}

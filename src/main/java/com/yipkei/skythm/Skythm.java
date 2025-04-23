package com.yipkei.skythm;

import com.yipkei.skythm.init.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Skythm implements ModInitializer {

    public static final String MOD_ID = "SKYTHM";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
    }
}

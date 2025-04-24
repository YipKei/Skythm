package com.yipkei.skythm.init;

import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Lifecycle;
import com.yipkei.skythm.consume.ModConsumeEffect;
import net.minecraft.Bootstrap;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryInfo;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;

import java.util.Map;
import java.util.function.Supplier;

public class ModRegistries {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Map<Identifier, Supplier<?>> DEFAULT_ENTRIES = Maps.<Identifier, Supplier<?>>newLinkedHashMap();
    private static final MutableRegistry<MutableRegistry<?>> ROOT = new SimpleRegistry<>(RegistryKey.ofRegistry(RegistryKeys.ROOT), Lifecycle.stable());
    public static final Registry<? extends Registry<?>> REGISTRIES = ROOT;

    public static final Registry<ModConsumeEffect.Type<?>> MOD_CONSUME_EFFECT_TYPE = create(RegistryKeys.CONSUME_EFFECT_TYPE, registry -> ModConsumeEffect.Type.APPLY_EFECTS);

    private static <T> Registry<T> create(RegistryKey<? extends Registry<T>> key, ModRegistries.Initializer<T> initializer) {
        return create(key, new SimpleRegistry<>(key, Lifecycle.stable(), false), initializer);
    }

    private static <T> Registry<T> createIntrusive(RegistryKey<? extends Registry<T>> key, ModRegistries.Initializer<T> initializer) {
        return create(key, new SimpleRegistry<>(key, Lifecycle.stable(), true), initializer);
    }

    private static <T> DefaultedRegistry<T> create(RegistryKey<? extends Registry<T>> key, String defaultId, ModRegistries.Initializer<T> initializer) {
        return create(key, new SimpleDefaultedRegistry<>(defaultId, key, Lifecycle.stable(), false), initializer);
    }

    private static <T> DefaultedRegistry<T> createIntrusive(RegistryKey<? extends Registry<T>> key, String defaultId, ModRegistries.Initializer<T> initializer) {
        return create(key, new SimpleDefaultedRegistry<>(defaultId, key, Lifecycle.stable(), true), initializer);
    }

    private static <T, R extends MutableRegistry<T>> R create(RegistryKey<? extends Registry<T>> key, R registry, ModRegistries.Initializer<T> initializer) {
        Bootstrap.ensureBootstrapped(() -> "registry " + key.getValue());
        Identifier identifier = key.getValue();
        DEFAULT_ENTRIES.put(identifier, (Supplier)() -> initializer.run(registry));
        ROOT.add((RegistryKey<MutableRegistry<?>>)key, registry, RegistryEntryInfo.DEFAULT);
        return registry;
    }

    public static void bootstrap() {
        init();
        freezeRegistries();
        validate(REGISTRIES);
    }

    private static void init() {
        DEFAULT_ENTRIES.forEach((id, initializer) -> {
            if (initializer.get() == null) {
                LOGGER.error("Unable to bootstrap registry '{}'", id);
            }
        });
    }

    private static void freezeRegistries() {
        REGISTRIES.freeze();

        for (Registry<?> registry : REGISTRIES) {
            resetTagEntries(registry);
            registry.freeze();
        }
    }

    private static <T extends Registry<?>> void validate(Registry<T> registries) {
        registries.forEach(registry -> {
            if (registry.getIds().isEmpty()) {
                Util.logErrorOrPause("Registry '" + registries.getId((T)registry) + "' was empty after loading");
            }

            if (registry instanceof DefaultedRegistry) {
                Identifier identifier = ((DefaultedRegistry)registry).getDefaultId();
                Validate.notNull(registry.get(identifier), "Missing default of DefaultedMappedRegistry: " + identifier);
            }
        });
    }

    public static <T> RegistryEntryLookup<T> createEntryLookup(Registry<T> registry) {
        return ((MutableRegistry)registry).createMutableRegistryLookup();
    }

    private static void resetTagEntries(Registry<?> registry) {
        ((SimpleRegistry)registry).resetTagEntries();
    }

    @FunctionalInterface
    interface Initializer<T> {
        Object run(Registry<T> registry);
    }

}

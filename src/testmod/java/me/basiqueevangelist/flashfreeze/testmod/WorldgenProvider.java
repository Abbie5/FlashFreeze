package me.basiqueevangelist.flashfreeze.testmod;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class WorldgenProvider extends FabricDynamicRegistryProvider {
    public WorldgenProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        final var biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.BIOME);
        entries.add(biomeRegistry, FlashFreezeTestMod.THONK);
    }

    @Override
    public String getName() {
        return "FlashFreeze Testmod Biomes";
    }
}

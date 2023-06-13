package me.basiqueevangelist.flashfreeze.testmod;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

public class FlashFreezeTestMod implements ModInitializer {
    public static final ComponentKey<TestComponent> TEST_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("flashfreeze:test"), TestComponent.class);
    public static final RegistryKey<Biome> THONK = RegistryKey.of(RegistryKeys.BIOME, new Identifier("flashfreeze:thonk"));

    public static void bootstrapBiomes(Registerable<Biome> biomeRegisterable) {
        var placedFeatures = biomeRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        var configuredCarvers = biomeRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);

        biomeRegisterable.register(THONK, makeThonk(placedFeatures, configuredCarvers));
    }

    private static Biome makeThonk(RegistryEntryLookup<PlacedFeature> placedFeature, RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers) {
        var generationSettings = new GenerationSettings.LookupBackedBuilder(placedFeature, configuredCarvers);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addExtraGoldOre(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addBadlandsGrass(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addBadlandsVegetation(generationSettings);
        return new Biome.Builder()
            .precipitation(false)
            .temperature(2.0F)
            .downfall(0)
            .effects(new BiomeEffects.Builder()
                .waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(0xffbbff)
                .skyColor(0xaaccdd)
                .build())
            .spawnSettings(new SpawnSettings.Builder().build())
            .generationSettings(generationSettings.build())
            .build();
    }

    @Override
    public void onInitialize() {
    }
}

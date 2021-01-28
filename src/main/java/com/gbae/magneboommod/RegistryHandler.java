package com.gbae.magneboommod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.ArrayList;

/**
 * Magneboom mod
 * @author GBAE
 */

@Mod.EventBusSubscriber // from TECHNOFREAK code for ore gen
public class RegistryHandler {
    // create DeferredRegister object
    private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<ConfiguredFeature<?, ?>>();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Magneboommod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Magneboommod.MODID);

    public static void init() {
        // attach DeferredRegister to the event bus
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // register blocks
            public static final RegistryObject<Block> MAGNEBOOM_ORE = BLOCKS.register("magneboom_ore", () ->
            new Block(
                    Block.Properties
                        .create(Material.IRON)
                        .hardnessAndResistance(3.0f, 3.0f)
                        .sound(SoundType.STONE)
                        .harvestLevel(2)
                        .harvestTool(ToolType.PICKAXE)
            )
    );

    public static final RegistryObject<Block> MAGNEBOOM_BLOCK_VOLATILE = BLOCKS.register("magneboom_block_volatile", () ->
            new Block(
                    Block.Properties
                            .create(Material.IRON)
                            .hardnessAndResistance(5.0f, 6.0f)
                            .sound(SoundType.STONE)
                            .harvestLevel(2)
                            .harvestTool(ToolType.PICKAXE)
            )
    );

            public static final RegistryObject<Block> MAGNEBOOM_BLOCK_STABLE = BLOCKS.register("magneboom_block_stable", () ->
            new Block(
                    Block.Properties
                            .create(Material.IRON)
                            .hardnessAndResistance(5.0f, 6.0f)
                            .sound(SoundType.STONE)
                            .harvestLevel(2)
                            .harvestTool(ToolType.PICKAXE)
            )
    );

    // register items
    public static final RegistryObject<Item> MAGNEBOOM_VOLATILE_ITEM = ITEMS.register("magneboom_volatile", () ->
            new Item(
                    new Item.Properties().group(ItemGroup.MATERIALS)
            )
    );

    public static final RegistryObject<Item> MAGNEBOOM_STABLE_ITEM = ITEMS.register("magneboom_stable", () ->
            new Item(
                    new Item.Properties().group(ItemGroup.MATERIALS)
            )
    );

    public static final RegistryObject<Item> MAGNEBOOM_ORE_ITEM = ITEMS.register("magneboom_ore", () ->
            new BlockItem(
                    MAGNEBOOM_ORE.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> MAGNEBOOM_BLOCK_VOLATILE_ITEM = ITEMS.register("magneboom_block_volatile", () ->
            new BlockItem(
                    MAGNEBOOM_BLOCK_VOLATILE.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> MAGNEBOOM_BLOCK_STABLE_ITEM = ITEMS.register("magneboom_block_stable", () ->
            new BlockItem(
                    MAGNEBOOM_BLOCK_STABLE.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );

    //ore registration, with code courtesy of TECHOFREAK

    public static void registerOres(){
        //BASE_STONE_OVERWORLD is for generating in stone, granite, diorite, and andesite
        //NETHERRACK is for generating in netherrack
        //BASE_STONE_NETHER is for generating in netherrack, basalt and blackstone

        //Overworld Ore Register
        overworldOres.add(register("magneboom_ore_gen", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, MAGNEBOOM_ORE.get().getDefaultState(), 4)) //Vein Size
                .range(64).square() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        for(ConfiguredFeature<?, ?> ore : overworldOres){
            if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Magneboommod.MODID + ":" + name, configuredFeature);
    }

}
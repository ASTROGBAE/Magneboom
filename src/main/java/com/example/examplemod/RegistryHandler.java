package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

public class RegistryHandler {
    // create DeferredRegister object
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);

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
}
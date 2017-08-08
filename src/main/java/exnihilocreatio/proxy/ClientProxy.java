package exnihilocreatio.proxy;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.ModFluids;
import exnihilocreatio.ModItems;
import exnihilocreatio.client.renderers.*;
import exnihilocreatio.entities.ProjectileStone;
import exnihilocreatio.items.ore.ItemOre;
import exnihilocreatio.registries.CompostRegistry;
import exnihilocreatio.registries.OreRegistry;
import exnihilocreatio.tiles.TileBarrel;
import exnihilocreatio.tiles.TileCrucible;
import exnihilocreatio.tiles.TileInfestedLeaves;
import exnihilocreatio.tiles.TileSieve;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@Mod.EventBusSubscriber
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocks.initModels(event);
        ModItems.initModels(event);
        ModFluids.initModels();

        registerRenderers();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        OreRegistry.initModels();
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new RenderOrePiece(), OreRegistry.getItemOreRegistry().toArray(new ItemOre[0]));

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public boolean runningOnServer() {
        return false;
    }

    public static void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileBarrel.class, new RenderBarrel());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSieve.class, new RenderSieve());
        ClientRegistry.bindTileEntitySpecialRenderer(TileInfestedLeaves.class, new RenderInfestedLeaves());
        RenderingRegistry.registerEntityRenderingHandler(ProjectileStone.class, new RenderProjectileStone.Factory());
    }

    @Override
    public void registerConfigs(File configDirectory) {
        CompostRegistry.recommendAllFood(new File(configDirectory, "RecommendedFoodRegistry.json"));
    }
}
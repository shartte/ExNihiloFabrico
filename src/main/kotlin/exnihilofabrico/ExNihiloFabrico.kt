package exnihilofabrico

import com.swordglowsblue.artifice.api.Artifice
import com.swordglowsblue.artifice.api.ArtificeResourcePack
import com.swordglowsblue.artifice.api.builder.assets.ModelBuilder
import com.swordglowsblue.artifice.api.util.Processor
import exnihilofabrico.api.registry.ExNihiloRegistries
import exnihilofabrico.api.registry.ExNihiloRegistries.CROOK
import exnihilofabrico.api.registry.ExNihiloRegistries.CRUCIBLE_HEAT
import exnihilofabrico.api.registry.ExNihiloRegistries.CRUCIBLE_STONE
import exnihilofabrico.api.registry.ExNihiloRegistries.CRUCIBLE_WOOD
import exnihilofabrico.api.registry.ExNihiloRegistries.HAMMER
import exnihilofabrico.api.registry.ExNihiloRegistries.MESH
import exnihilofabrico.api.registry.ExNihiloRegistries.ORES
import exnihilofabrico.api.registry.ExNihiloRegistries.SIEVE
import exnihilofabrico.api.registry.ExNihiloRegistries.WITCHWATER_WORLD
import exnihilofabrico.client.FluidRenderManager
import exnihilofabrico.client.ExNihiloItemColorProvider
import exnihilofabrico.client.renderers.CrucibleBlockEntityRenderer
import exnihilofabrico.client.renderers.SieveBlockEntityRenderer
import exnihilofabrico.common.ModBlocks
import exnihilofabrico.common.ModFluids
import exnihilofabrico.common.ModItems
import exnihilofabrico.common.ModTools
import exnihilofabrico.common.crucibles.CrucibleBlockEntity
import exnihilofabrico.common.sieves.SieveBlockEntity
import exnihilofabrico.modules.MetaModule
import exnihilofabrico.util.ExNihiloItemStack
import io.github.cottonmc.cotton.logging.ModLogger
import net.fabricmc.api.*
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.item.Items
import net.minecraft.tag.ItemTags
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

const val MODID: String = "exnihilofabrico"
const val VERSION: String = "0.0a"

// Small helper functions
fun id(name: String) = Identifier(MODID, name)

object ExNihiloFabrico: ModInitializer {
    val ITEM_GROUP = FabricItemGroupBuilder.build(Identifier(MODID, "general")) { ExNihiloItemStack("crook_wood") }
//    val LOGGER = ModLogger(MODID, "ExNihiloFabrico")
    val LOGGER = ModLogger(MODID)

    override fun onInitialize() {
        loadConfigs()

        /* OreRegistry and MeshRegistry is used to create items so must be first */
        MetaModule.registerOres(ORES)
        MetaModule.registerMesh(MESH)
        /* Register Fluids*/
        LOGGER.info("Registering Fluids")
        ModFluids.registerFluids(Registry.FLUID)
        LOGGER.info("Registered Fluids")
        /* Register Blocks */
        LOGGER.info("Registering Blocks")
        ModBlocks.registerBlocks(Registry.BLOCK)
        LOGGER.info("Registered Blocks")
        /* Register Items */
        LOGGER.info("Registering Items")
        ModBlocks.registerBlockItems(Registry.ITEM)
        ModItems.registerItems(Registry.ITEM)
        ModTools.registerItems(Registry.ITEM)
        LOGGER.info("Registered Items")

        /* Register Block Entities */
        LOGGER.info("Registering Block Entities")
        ModBlocks.registerBlockEntities(Registry.BLOCK_ENTITY)
        LOGGER.info("Registered Block Entities")

        /* Load the rest of the Ex Nihilo Fabrico registries */
        ORES.registerCraftingRecipes()
        loadRegistries()
        generateRecipes()
    }

    private fun loadConfigs() {

    }

    private fun loadRegistries() {
        // Barrel Recipes
        // MetaModule.registerBarrelAlchemy(BARREL_ALCHEMY)
        // MetaModule.registerBarrelMilking(BARREL_MILKING)
        // Crucible Recipes
        MetaModule.registerCrucibleHeat(CRUCIBLE_HEAT)
        MetaModule.registerCrucibleStone(CRUCIBLE_STONE)
        MetaModule.registerCrucibleWood(CRUCIBLE_WOOD)
        // Sieve Recipes
        MetaModule.registerSieve(SIEVE)
        // Tool Recipes
        MetaModule.registerHammer(HAMMER)
        MetaModule.registerCrook(CROOK)
        // Witch Water Recipes
        MetaModule.registerWitchWaterFluid(WITCHWATER_WORLD)
        // Misc. Files
    }

    private fun generateRecipes() {
        val dataPack = Artifice.registerData(MODID) {builder ->
            // Ore Chunk Crafting
            for(ore in ExNihiloRegistries.ORES.getAll())
                builder.addShapedRecipe(ore.getChunkID()) { ore.generateRecipe(it) }
            // Mesh Crafting
            for(mesh in ExNihiloRegistries.MESH.getAll())
                builder.addShapedRecipe(mesh.identifier) { mesh.generateRecipe(it) }
            // Mesh Crafting
            for((k, sieve) in ModBlocks.SIEVES)
                builder.addShapedRecipe(k) { sieve.generateRecipe(it) }
            // Crucible Crafting
            for((k, crucible) in ModBlocks.CRUCIBLES)
                builder.addShapedRecipe(k) { crucible.generateRecipe(it) }
            // Barrel Crafting
            for((k, barrel) in ModBlocks.BARRELS)
                builder.addShapedRecipe(k) { barrel.generateRecipe(it) }
        }

    }
}
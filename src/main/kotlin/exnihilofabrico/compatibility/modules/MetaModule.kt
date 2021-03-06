package exnihilofabrico.compatibility.modules

import exnihilofabrico.api.compatibility.IExNihiloFabricoModule
import exnihilofabrico.api.registry.*

object MetaModule: IExNihiloFabricoModule {

    val modules: MutableList<IExNihiloFabricoModule> = mutableListOf()

    override fun registerAlchemy(registry: IAlchemyRegistry) = modules.forEach { it.registerAlchemy(registry) }
    override fun registerCompost(registry: ICompostRegistry) = modules.forEach { it.registerCompost(registry) }
    override fun registerLeaking(registry: ILeakingRegistry) = modules.forEach { it.registerLeaking(registry) }
    override fun registerFluidOnTop(registry: IFluidOnTopRegistry) = modules.forEach { it.registerFluidOnTop(registry) }
    override fun registerFluidTransform(registry: IFluidTransformRegistry) = modules.forEach { it.registerFluidTransform(registry) }

    override fun registerMilking(registry: IMilkingRegistry) = modules.forEach { it.registerMilking(registry) }

    override fun registerCrucibleHeat(registry: ICrucibleHeatRegistry) = modules.forEach { it.registerCrucibleHeat(registry) }
    override fun registerCrucibleStone(registry: ICrucibleRegistry) = modules.forEach { it.registerCrucibleStone(registry) }
    override fun registerCrucibleWood(registry: ICrucibleRegistry) = modules.forEach { it.registerCrucibleWood(registry) }

    override fun registerOres(registry: IOreRegistry) = modules.forEach { it.registerOres(registry) }

    override fun registerSieve(registry: ISieveRegistry) = modules.forEach { it.registerSieve(registry) }
    override fun registerMesh(registry: IMeshRegistry) = modules.forEach { it.registerMesh(registry) }

    override fun registerCrook(registry: IToolRegistry) = modules.forEach { it.registerCrook(registry) }
    override fun registerHammer(registry: IToolRegistry) = modules.forEach { it.registerHammer(registry) }

    override fun registerWitchWaterWorld(registry: IWitchWaterWorldRegistry) = modules.forEach { it.registerWitchWaterWorld(registry) }
    override fun registerWitchWaterEntity(registry: IWitchWaterEntityRegistry) = modules.forEach { it.registerWitchWaterEntity(registry) }
}
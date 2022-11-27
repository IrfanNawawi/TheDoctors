package id.heycoding.thedoctors.di

import org.koin.core.module.Module

object MainModuleProvider {
    private val mainModuleContainer = MainModuleContainer()

    // reflection
    fun mainModules(): List<Module> {
        val declareModule = mainModuleContainer::class.java.declaredFields.map {
            it.isAccessible = true
            it.get(mainModuleContainer) as Module
        }
        return declareModule
    }
}
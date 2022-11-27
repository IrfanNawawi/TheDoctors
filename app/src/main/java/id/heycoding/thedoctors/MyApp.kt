package id.heycoding.thedoctors

import android.app.Application
import id.heycoding.thedoctors.di.MainModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val mainModules = MainModuleProvider.mainModules()

        startKoin {
            androidContext(this@MyApp)
            modules(mainModules)
        }

    }

}
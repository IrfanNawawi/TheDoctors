package id.heycoding.thedoctors.di

import id.heycoding.thedoctors.data.remote.MainWebServices
import id.heycoding.thedoctors.data.remote.RetrofitProvider
import id.heycoding.thedoctors.data.repository.MainRepository
import id.heycoding.thedoctors.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainModuleContainer {
    private val webServiceModule = module {
        single<MainWebServices> { RetrofitProvider.retrofit().create(MainWebServices::class.java) }
    }

    private val repositoryModule = module {
        factory<MainRepository> { MainRepository(get()) }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }
}
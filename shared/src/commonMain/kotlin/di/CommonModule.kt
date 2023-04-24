package di

import domain.detail.DetailGetUseCase
import domain.list.ListGetUseCase
import org.koin.dsl.module
import presentation.detail.DetailViewStateModel
import presentation.list.ListViewStateModel

fun useCaseModule() = module {
    factory { ListGetUseCase() }
    factory { DetailGetUseCase() }
}

fun screenStateModelModule() = module {
    single { ListViewStateModel(listGetUseCase = get()) }
    single { DetailViewStateModel(detailGetUseCase = get()) }
}
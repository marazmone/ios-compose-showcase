package di

import cache.db.sqldelight.entity.currency.CurrencyEntity
import data.datasource.currency.CurrencyCacheDataSource
import data.datasource.currency.CurrencyCacheDataSourceImpl
import data.datasource.currency.CurrencyRemoteDataSource
import data.datasource.currency.CurrencyRemoteDataSourceImpl
import data.db.createDatabase
import data.mapper.currency.CurrencyResponseToEntityMapper
import data.remote.RemoteConst
import data.remote.response.CurrencyResponse
import data.repository.CurrencyRepositoryImpl
import domain.detail.DetailGetUseCase
import domain.mapper.base.Mapper
import domain.mapper.currency.CurrencyEntityToDetailMapper
import domain.mapper.currency.CurrencyEntityToListItemMapper
import domain.model.currency.CurrencyDetail
import domain.model.currency.CurrencyListItem
import domain.repository.currency.CurrencyRepository
import domain.usecase.currency.CurrencyGetAllUseCase
import domain.usecase.currency.CurrencyObserveAllUseCase
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import presentation.detail.DetailViewStateModel
import presentation.list.ListViewStateModel

fun cacheModule() = module {
    single {
        createDatabase(get())
    }
}

fun remoteModule() = module {
    single {
        HttpClient(get()) {
            defaultRequest {
                url {
                    host = RemoteConst.Url.BASE
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = "HTTP Client", message = message)
                    }
                }
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30_000L
                connectTimeoutMillis = 30_000L
                socketTimeoutMillis = 30_000L
            }
        }.also {
            Napier.base(DebugAntilog())
        }
    }
}

fun mapperModule() = module {
    factory<Mapper<CurrencyResponse, CurrencyEntity>> {
        CurrencyResponseToEntityMapper()
    }
    factory<Mapper<CurrencyEntity, CurrencyListItem>> {
        CurrencyEntityToListItemMapper()
    }
    factory<Mapper<CurrencyEntity, CurrencyDetail>> {
        CurrencyEntityToDetailMapper()
    }
}

fun dataSourceModule() = module {
    single<CurrencyRemoteDataSource> {
        CurrencyRemoteDataSourceImpl(get())
    }
    single<CurrencyCacheDataSource> {
        CurrencyCacheDataSourceImpl(get())
    }
}

fun repositoryModule() = module {
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            remote = get(),
            cache = get(),
            responseToEntityMapper = get(),
            entityToListItemMapper = get(),
            entityToDetailMapper = get(),
        )
    }
}

fun useCaseModule() = module {
    factory { CurrencyGetAllUseCase(get()) }
    factory { CurrencyObserveAllUseCase(get()) }
    factory { DetailGetUseCase() }
}

fun screenStateModelModule() = module {
    single { ListViewStateModel(get(), get()) }
    single { DetailViewStateModel(detailGetUseCase = get()) }
}
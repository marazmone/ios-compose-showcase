package di

import data.cache.entity.CountryEntity
import data.datasource.CountryCacheDataSource
import data.datasource.CountryCacheDataSourceImpl
import data.datasource.CountryRemoteDataSource
import data.datasource.CountryRemoteDataSourceImpl
import data.mapper.CountryEntityToModelMapper
import data.mapper.CountryResponseToEntityMapper
import data.remote.RemoteConst
import data.remote.response.CountryResponse
import data.repository.CountryRepositoryImpl
import domain.mapper.base.Mapper
import domain.model.CountryModel
import domain.repository.CountryRepository
import domain.usecase.detail.DetailGetUseCase
import domain.usecase.list.CountryGetAllRemoteUseCase
import domain.usecase.list.CountryObserveAllCacheUseCase
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
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.tabs.list.detail.DetailViewStateModel
import presentation.tabs.list.ListViewStateModel

fun cacheModule() = module {
    single {
        val builder = RealmConfiguration.Builder(
            schema = setOf(
                CountryEntity::class,
            )
        )
        builder.schemaVersion(1)
        builder.deleteRealmIfMigrationNeeded()
        Realm.open(builder.build())
    }
}

fun remoteModule() = module {
    single {
        HttpClient(get()) {
            defaultRequest {
                url {
                    host = RemoteConst.Url.Base
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
    factory<Mapper<CountryResponse, CountryEntity>>(named(CountryResponseToEntityMapper.NAMED)) {
        CountryResponseToEntityMapper()
    }
    factory<Mapper<CountryEntity, CountryModel>>(named(CountryEntityToModelMapper.NAMED)) {
        CountryEntityToModelMapper()
    }
}

fun dataSourceModule() = module {
    single<CountryRemoteDataSource> {
        CountryRemoteDataSourceImpl(get())
    }
    single<CountryCacheDataSource> {
        CountryCacheDataSourceImpl(get())
    }
}

fun repositoryModule() = module {
    single<CountryRepository> {
        CountryRepositoryImpl(
            remote = get(),
            cache = get(),
            responseToEntityMapper = get(named(CountryResponseToEntityMapper.NAMED)),
            entityToModelMapper = get(named(CountryEntityToModelMapper.NAMED)),
        )
    }
}

fun useCaseModule() = module {
    factory { CountryGetAllRemoteUseCase(get()) }
    factory { CountryObserveAllCacheUseCase(get()) }
    factory { DetailGetUseCase() }
}

fun screenStateModelModule() = module {
    single {
        ListViewStateModel(
            countryGetAllRemoteUseCase = get(),
            countryObserveAllCacheUseCase = get(),
        )
    }
    single { DetailViewStateModel(detailGetUseCase = get()) }
}
package di

import data.db.DriverFactory
import io.ktor.client.engine.android.Android
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { Android.create() }
    single { DriverFactory(get()) }
}
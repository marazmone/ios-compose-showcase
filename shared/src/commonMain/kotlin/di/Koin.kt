package di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// Android
fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(appModules())
}

// IOS
fun initKoin() = initKoin {}
package di

fun appModules() = listOf(
    useCaseModule(),
    screenStateModelModule(),
    platformModule(),
    cacheModule(),
    remoteModule(),
    mapperModule(),
    repositoryModule(),
    dataSourceModule(),
)
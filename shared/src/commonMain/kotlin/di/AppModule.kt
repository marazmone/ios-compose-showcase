package di

fun appModules() = listOf(
    useCaseModule(),
    screenStateModelModule(),
    platformModule()
)
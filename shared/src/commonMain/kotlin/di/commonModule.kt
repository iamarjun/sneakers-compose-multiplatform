package di

import Greeting
import domain.SneakersRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import network.SneakersApiImp
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import usecases.CartManager
import usecases.GetSneakerList

val commonModule = module {
    singleOf(::Greeting)
    single { provideJsonSerializer() }
    single { provideHttpClient(get()) }
    singleOf(::SneakersApiImp)
    singleOf(::SneakersRepository)
    singleOf(::GetSneakerList)
    singleOf(::CartManager)
}

fun provideHttpClient(json: Json) = HttpClient {
    install(ContentNegotiation) {
        json(json)
    }
}

fun provideJsonSerializer(): Json = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}


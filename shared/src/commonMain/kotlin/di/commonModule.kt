package di

import Greeting
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import network.SneakersApiImp
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import usecases.GetSneakers

val commonModule = module {
    singleOf(::Greeting)
    single { provideHttpClient() }
    singleOf(::SneakersApiImp)
    singleOf(::GetSneakers)
}

fun provideHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}


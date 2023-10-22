package di

import Greeting
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::Greeting)
    single { provideHttpClient() }
//    singleOf(::BirdApiImp)
//    singleOf(::GetBirdList)
}

fun provideHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}


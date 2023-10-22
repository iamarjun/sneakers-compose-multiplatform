package com.myapplication.di

import com.myapplication.GreetingPresenter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val androidModule = module {
    singleOf(::GreetingPresenter)
}
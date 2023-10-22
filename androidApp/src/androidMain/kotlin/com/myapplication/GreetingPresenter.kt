package com.myapplication

import Greeting

class GreetingPresenter(val greeting: Greeting) {
    fun print() = greeting.greeting()
}
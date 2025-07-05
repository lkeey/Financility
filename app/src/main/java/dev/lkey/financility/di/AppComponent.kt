package dev.lkey.financility.di

import dagger.Component
import dev.lkey.financility.MainApplication

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: MainApplication)
}

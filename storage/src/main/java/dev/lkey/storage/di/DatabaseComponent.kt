package dev.lkey.storage.di

import dagger.Component
import dev.lkey.core.di.CoreComponent
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.di.module.AppRoomModule
import dev.lkey.storage.di.module.ArticlesStorageModule


/**
 * Компонент, который хранит в себе базу данных для офлайн-режима
 */

@DatabaseScope
@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [
        AppRoomModule::class,
        ArticlesStorageModule::class
    ]
)
interface DatabaseComponent {

    fun categoryDao(): CategoryDao

    fun accountDao(): AccountDao

    fun transactionDao(): TransactionDao

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): DatabaseComponent
    }

}
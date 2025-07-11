package dev.lkey.transations.di

import jakarta.inject.Scope

/**
 * Scope nраназакций
 * указывает на жизненный цикл компонента
 */


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TransactionScope
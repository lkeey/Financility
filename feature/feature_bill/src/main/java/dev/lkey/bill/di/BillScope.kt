package dev.lkey.bill.di

import jakarta.inject.Scope

/**
 * Scope счетов
 * указывает на жизненный цикл компонента
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class BillScope

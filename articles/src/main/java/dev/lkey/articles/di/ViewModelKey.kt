package dev.lkey.articles.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Dagger сам по себе не знает, какие ключи использовать
 * в Map<Class<out ViewModel>, Provider<ViewModel>>
 */


@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)

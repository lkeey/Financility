package dev.lkey.core.di.utils

import dev.lkey.core.di.CoreComponent

/**
 * Интерфейс для имплементации [CoreComponent]
 */

interface CoreProvider {
    val coreComponent: CoreComponent
}

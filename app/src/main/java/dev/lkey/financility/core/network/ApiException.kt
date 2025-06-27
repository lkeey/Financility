package dev.lkey.financility.core.network

/**
 * Кастомный класс ошибок
 *
 * Служит для отображения ошибок пользователю
 */

class ApiException(message: String) : Exception(message)

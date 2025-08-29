package dev.lkey.settings.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.lkey.settings.presentation.ui.code.CodeSettingsView
import dev.lkey.settings.presentation.viewmodel.SettingsEvent
import dev.lkey.settings.presentation.viewmodel.SettingsState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CodeSettingsViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pinInput_acceptsUpTo4Digits_andCallsOnEvent() {
        // Переменная для проверки вызова onEvent
        val events = mutableListOf<SettingsEvent>()

        // Изначальный state с stage 0 (ввод первого PIN)
        val state = SettingsState(
            stage = 0,
            firstEntry = "",
            confirmEntry = "",
            status = dev.lkey.core.network.FinancilityResult.Success
        )

        composeTestRule.setContent {
            // Чтобы onEvent сохранял вызовы в список
            CodeSettingsView(
                state = state,
                onEvent = { events.add(it) }
            )
        }

        val pinInput = composeTestRule.onNodeWithTag("PinInput")

        // Вводим 5 символов - больше 4 не принимается
        pinInput.performTextInput("12345")

        // Проверяем, что в поле максимум 4 символа
        pinInput.assertTextEquals("1234")

        // Проверяем, что onEvent вызывался с правильными значениями (тестируем, что вызовы с 1,2,3,4 символами)
        val enteredPins = events.filterIsInstance<SettingsEvent.OnEnterPin>().map { it.pin }
        assert(enteredPins.contains("1"))
        assert(enteredPins.contains("12"))
        assert(enteredPins.contains("123"))
        assert(enteredPins.contains("1234"))
        // "12345" не должно попасть, потому что длина больше 4
        assert(!enteredPins.contains("12345"))
    }

    @Test
    fun textChanges_whenStageChanges() {
        // Проверяем, что текст в поле меняется в зависимости от stage и отображается правильный текст над полем
        val stateStage0 = SettingsState(
            stage = 0,
            firstEntry = "12",
            confirmEntry = "",
            status = dev.lkey.core.network.FinancilityResult.Success
        )

        val stateStage1 = SettingsState(
            stage = 1,
            firstEntry = "12",
            confirmEntry = "34",
            status = dev.lkey.core.network.FinancilityResult.Success
        )

        // Stage 0 - текст "Введите PIN"
        composeTestRule.setContent {
            CodeSettingsView(
                state = stateStage0,
                onEvent = {}
            )
        }
        composeTestRule.onNodeWithText("Введите PIN").assertIsDisplayed()
        composeTestRule.onNodeWithTag("PinInput").assertTextEquals("12")

        // Stage 1 - текст "Повторите PIN"
        composeTestRule.setContent {
            CodeSettingsView(
                state = stateStage1,
                onEvent = {}
            )
        }
        composeTestRule.onNodeWithText("Повторите PIN").assertIsDisplayed()
        composeTestRule.onNodeWithTag("PinInput").assertTextEquals("34")
    }
}
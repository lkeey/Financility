package dev.lkey.common.haptics

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresPermission
import dev.lkey.common.core.model.HapticEffect
import dev.lkey.common.core.model.HapticSettings

/**
 * Здесь происходит вызов вибрации
 * - учитывается SDK версия устройства
 */

@RequiresPermission(Manifest.permission.VIBRATE)
fun performHaptic(
    context: Context,
    settings: HapticSettings
) {
    if (!settings.enabled) return

    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vm = context.getSystemService(VibratorManager::class.java)
        vm?.defaultVibrator
    } else {
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    } ?: return

    if (!(vibrator.hasVibrator())) return

    val effect = when (settings.effect) {
        HapticEffect.CLICK -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
            } else {
                VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE)
            }
        }
        HapticEffect.HEAVY_CLICK -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            } else {
                VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
            }
        }
        HapticEffect.DOUBLE_CLICK -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK)
            } else {
                VibrationEffect.createWaveform(longArrayOf(0, 30, 40, 30), -1)
            }
        }
        HapticEffect.TICK -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
            } else {
                VibrationEffect.createOneShot(15, VibrationEffect.DEFAULT_AMPLITUDE)
            }
        }
        HapticEffect.CUSTOM_SHORT -> {
            VibrationEffect.createOneShot(20, 128)
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        vibrator.vibrate(effect)
    } else {
        vibrator.vibrate(effect)
    }
}

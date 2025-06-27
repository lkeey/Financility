package dev.lkey.financility.core.converter

fun Double.toFormat() : String {
    return "%.2f".format(this)
}

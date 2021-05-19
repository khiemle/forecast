package com.khiemle.nab.device

import java.util.*

class DeviceInfo(val isRooted: Boolean = false)


val k = mapOf("1" to 2, "2" to 3)

fun romanToInt(s: String): Int {
    val pairs = mapOf(
        "IV" to 4,
        "IX" to 9,
        "XL" to 40,
        "XC" to 90,
        "CD" to 400,
        "CM" to 900
    )
    val alone = mapOf(
        "I" to 1,
        "V" to 5,
        "X" to 10,
        "L" to 50,
        "C" to 100,
        "D" to 500,
        "M" to 1000
    )
    var gs = s
    var sum = 0
    pairs.forEach { entry ->
        val (romanPair, value) = entry
        while (gs.isEmpty().not() && gs.indexOf(romanPair) >= 0) {
            sum += value
            gs = gs.replaceFirst(romanPair,"")
        }
    }

    var tempMap = mutableMapOf<Char, Int>()

    for (i in gs.indices) {
        val current = tempMap[gs[i]] ?: 0
        tempMap[gs[i]] = current + 1
    }

    tempMap.forEach { entry ->
        val (key, value) = entry
        sum += value *( alone[key.toString()] ?: 0)
    }
    val str = "{}{}"
    val opened = setOf("(", "{", "[")
    opened.contains(str[0])
    val stk = Stack<String>()
    stk.si
    return sum
}

fun main() {
    print(romanToInt("LVIII"))
}
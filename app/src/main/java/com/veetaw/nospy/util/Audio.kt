package com.veetaw.nospy.util

import java.io.File

class Audio {

    private val card: CharArray

    init {
        card = this.getCard()
    }

    private fun getCard(): CharArray {
        val card = charArrayOf('0', '0')

        val path = "/proc/asound/devices"
        val file = File(path).inputStream().bufferedReader().use { it.readText() }
        val pattern = Regex("\\d+: \\[ (\\d)- (\\d)]: digital audio capture")

        try {
            card[0] = pattern.matchEntire(pattern.findAll(file).toList()[0].value)?.groups?.get(1)?.value?.single() as Char
            card[1] = pattern.matchEntire(pattern.findAll(file).toList()[0].value)?.groups?.get(2)?.value?.single() as Char
        } catch (e: Exception) {
        } // do nothing, ['0', '0'] will be used

        return card
    }

    fun isUsed(): Boolean {
        val path = "/proc/asound/card" + card[0] + "/pcm" + card[1] + "c/info"
        val file = File(path).inputStream().bufferedReader().use { it.readText() }

        var pattern = Regex("subdevices_count: (\\d)")
        val total = Integer.parseInt(pattern.matchEntire(pattern.findAll(file).toList()[0].value)?.groups?.get(1)?.value)

        pattern = Regex("subdevices_avail: (\\d)")
        val available = Integer.parseInt(pattern.matchEntire(pattern.findAll(file).toList()[0].value)?.groups?.get(1)?.value)

        return total - available > 0
    }
}

package com.veetaw.nospy.util

import java.io.File

class Audio {

    private val card: CharArray

    init {
        card = this.getCard()
    }

    private val general_path = "/proc/asound/card" + card[0] + "/pcm" + card[1] + "c/"

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
        val path = general_path + "info"
        val file = File(path).inputStream().bufferedReader().use { it.readText() }

        var pattern = Regex("subdevices_count: (\\d)")
        val total = pattern.matchEntire(pattern.findAll(file).toList()[0].value)?.groups?.get(1)?.value!!.toInt()

        pattern = Regex("subdevices_avail: (\\d)")
        val available = pattern.matchEntire(pattern.findAll(file).toList()[0].value)?.groups?.get(1)?.value!!.toInt()

        return total - available > 0
    }

    /*
    * function is good, but owner_pid is wrong. todo
    */
    fun getPID(): Int {
        if (!isUsed()) return -1

        val path = general_path + "sub0/status"
        val file = File(path).inputStream().bufferedReader().use { it.readText() }

        val pattern = Regex("owner_pid .+ (\\d+)")

        return pattern.matchEntire(pattern.findAll(file).toList()[0].value)?.groups?.get(1)?.value!!.toInt()
    }
}

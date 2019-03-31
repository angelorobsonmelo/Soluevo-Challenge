package com.angelomelo.soluevochallenge.application.utils

import android.os.Build
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object RandomUtil {

    @JvmStatic
    fun getRandomNumber(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ThreadLocalRandom.current().nextInt(100000000, 900000000)
        } else {
            Random().nextInt(900000001) + 100000001
        }
    }
}

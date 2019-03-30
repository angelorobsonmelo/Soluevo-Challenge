package com.angelomelo.soluevochallenge.application.utils.extensions

import java.math.BigInteger

fun String.extractNumbers(): BigInteger {
   return  this.replace(Regex("[^0-9]"), "").toBigInteger()
}
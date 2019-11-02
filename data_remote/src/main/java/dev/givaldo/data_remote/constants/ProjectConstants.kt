package dev.givaldo.data_remote.constants

import java.util.concurrent.TimeUnit

object ProjectConstants {

    val CONNECT_TIMEOUT = 30L to TimeUnit.SECONDS
    val READ_TIMEOUT = 30L to TimeUnit.SECONDS
    val WRITE_TIMEOUT = 30L to TimeUnit.SECONDS

}
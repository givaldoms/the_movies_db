package dev.givaldo.data_local.factory

interface EntityFactory<T> {

    fun dumbInstance(): T

    fun makeDumbList(size: Int = 5) = mutableListOf<T>().apply {
        repeat(size) {
            add(dumbInstance())
        }
    }.toList()
}
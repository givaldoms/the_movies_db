package dev.givaldo.data_remote.mapper

interface DataRemoteMapper<in R, out D> {

    fun toDomain(remote: R): D

    fun toDomain(remote: List<R>): List<D> = remote.map { toDomain(it) }

}
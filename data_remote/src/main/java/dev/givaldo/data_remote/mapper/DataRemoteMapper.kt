package dev.givaldo.data_remote.mapper

abstract class DataRemoteMapper<in R, out D> {

    abstract fun toDomain(remote: R): D

}
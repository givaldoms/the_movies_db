package dev.givaldo.data_local.mapper

abstract class DataLocalMapper<L, D> {

    abstract fun toDomain(remote: L): D

    abstract fun fromDomain(domain: D): L

}
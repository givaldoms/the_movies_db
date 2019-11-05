package dev.givaldo.data_local.mapper

interface DataLocalMapper<L, D> {

    fun toDomain(remote: L): D

    fun fromDomain(domain: D): L

    fun toDomain(remote: List<L>): List<D> = remote.map { toDomain(it) }

    fun fromDomain(domain: List<D>): List<L> = domain.map { fromDomain(it) }

}
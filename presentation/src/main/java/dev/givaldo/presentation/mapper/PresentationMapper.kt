package dev.givaldo.presentation.mapper

interface PresentationMapper<P, D> {

    fun fromDomain(domain: D): P

    fun toDomain(presentation: P): D

    fun fromDomain(list: List<D>): List<P> = list.map { fromDomain(it) }

}
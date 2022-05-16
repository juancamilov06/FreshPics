package com.juanvilla.freshpic.data.mappers

abstract class DbMapper<DOMAIN, DB> {
    abstract fun mapDomainToDb(domain: DOMAIN): DB
    abstract fun mapDbToDomain(db: DB): DOMAIN

    fun mapDomainListToDbList(domain: List<DOMAIN>): List<DB> =
        domain.map {
            mapDomainToDb(it)
        }

    fun mapDbListToDomainList(db: List<DB>): List<DOMAIN> =
        db.map {
            mapDbToDomain(it)
        }
}
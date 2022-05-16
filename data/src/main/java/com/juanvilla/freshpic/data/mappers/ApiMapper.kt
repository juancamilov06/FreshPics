package com.juanvilla.freshpic.data.mappers

abstract class ApiMapper<DOMAIN, API> {
    abstract fun mapDomainToApi(domain: DOMAIN): API
    abstract fun mapApiToDomain(api: API): DOMAIN

    fun mapDomainListToApiList(domain: List<DOMAIN>): List<API> =
        domain.map {
            mapDomainToApi(it)
        }

    fun mapApiListToDomainList(api: List<API>): List<DOMAIN> =
        api.map {
            mapApiToDomain(it)
        }
}
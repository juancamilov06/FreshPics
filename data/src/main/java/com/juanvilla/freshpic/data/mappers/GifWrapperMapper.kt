package com.juanvilla.freshpic.data.mappers

import com.juanvilla.freshpic.data.source.remote.entities.ApiGifWrapper
import com.juanvilla.freshpic.domain.entity.GifWrapper

object GifWrapperMapper : ApiMapper<GifWrapper, ApiGifWrapper>() {

    private val gifMapper = ApiGifMapper

    override fun mapDomainToApi(domain: GifWrapper): ApiGifWrapper = ApiGifWrapper(
        gifs = gifMapper.mapDomainListToApiList(domain.gifs),
        pagination = null,
        meta = null
    )

    override fun mapApiToDomain(api: ApiGifWrapper): GifWrapper = GifWrapper(
        gifs = gifMapper.mapApiListToDomainList(api.gifs)
    )
}
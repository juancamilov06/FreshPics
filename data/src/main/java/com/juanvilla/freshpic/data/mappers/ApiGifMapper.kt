package com.juanvilla.freshpic.data.mappers

import com.juanvilla.freshpic.data.source.remote.entities.ApiGif
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.Image

object ApiGifMapper : ApiMapper<Gif, ApiGif>() {
    override fun mapDomainToApi(domain: Gif): ApiGif = ApiGif(
        type = domain.type,
        id = domain.id,
        slug = domain.slug,
        url = domain.url,
        bitlyUrl = domain.bitlyUrl,
        embedUrl = domain.embedUrl,
        username = domain.username,
        source = domain.source,
        rating = domain.rating,
        updateDateTime = domain.updateDateTime,
        createDateTime = domain.createDateTime,
        importDateTime = domain.importDateTime,
        trendingDateTime = domain.trendingDateTime,
        title = domain.title,
        user = null,
        images = null
    )

    override fun mapApiToDomain(api: ApiGif): Gif = Gif(
        type = api.type,
        id = api.id,
        slug = api.slug,
        url = api.url,
        bitlyUrl = api.bitlyUrl,
        embedUrl = api.embedUrl,
        username = api.username,
        source = api.source,
        rating = api.rating,
        updateDateTime = api.updateDateTime,
        createDateTime = api.createDateTime,
        importDateTime = api.importDateTime,
        trendingDateTime = api.trendingDateTime,
        title = api.title,
        user = null,
        image = Image(
            height = api.images!!.original.height,
            width = api.images.original.width,
            url = api.images.original.url
        ),
        isFavorite = api.isFavorite
    )
}
package com.juanvilla.freshpic.data.mappers

import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.Image

object DbGifMapper : DbMapper<Gif, DbGif>() {
    override fun mapDomainToDb(domain: Gif): DbGif = DbGif(
        rowId = 0,
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
        height = domain.image.height,
        width = domain.image.width,
        contentUrl = domain.image.url,
        isFavorite = true
    )

    override fun mapDbToDomain(db: DbGif): Gif = Gif(
        type = db.type,
        id = db.id,
        slug = db.slug,
        url = db.url,
        bitlyUrl = db.bitlyUrl,
        embedUrl = db.embedUrl,
        username = db.username,
        source = db.source,
        rating = db.rating,
        updateDateTime = db.updateDateTime,
        createDateTime = db.createDateTime,
        importDateTime = db.importDateTime,
        trendingDateTime = db.trendingDateTime,
        title = db.title,
        user = null,
        isFavorite = db.isFavorite,
        image = Image(
            height = db.height,
            width = db.width,
            url = db.contentUrl
        )
    )
}
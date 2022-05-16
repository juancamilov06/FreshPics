package com.juanvilla.freshpic.data.mappers

import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.domain.entity.Gif

object DbGifMapper : DbMapper<Gif, DbGif>() {
    override fun mapDomainToDb(domain: Gif): DbGif {
        TODO("Not yet implemented")
    }

    override fun mapDbToDomain(db: DbGif): Gif {
        TODO("Not yet implemented")
    }
}
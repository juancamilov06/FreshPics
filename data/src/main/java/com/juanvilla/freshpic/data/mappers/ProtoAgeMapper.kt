package com.juanvilla.freshpic.data.mappers

import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import com.juanvilla.freshpic.domain.entity.AgeControlPreferences

object ProtoAgeMapper : DbMapper<AgeControlPreferences, ProtoAgeControlPreferences>() {

    override fun mapDomainToDb(domain: AgeControlPreferences): ProtoAgeControlPreferences =
        ProtoAgeControlPreferences(
            isAdult = domain.isAdult,
            selectedRating = domain.selectedRating
        )

    override fun mapDbToDomain(db: ProtoAgeControlPreferences): AgeControlPreferences =
        AgeControlPreferences(
            isAdult = db.isAdult,
            selectedRating = db.selectedRating
        )

}
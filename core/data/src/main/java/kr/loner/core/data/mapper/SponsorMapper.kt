package kr.loner.core.data.mapper

import kr.loner.core.data.model.SponsorResponse
import kr.loner.core.model.Sponsor

internal fun SponsorResponse.toEntity() = Sponsor(
    name = name,
    imageUrl = imageUrl,
    homepage = homepage,
    grade = grade.toEntity(),
)

internal fun SponsorResponse.Grade.toEntity(): Sponsor.Grade {
    return when (this) {
        SponsorResponse.Grade.PLATINUM -> Sponsor.Grade.PLATINUM
        SponsorResponse.Grade.GOLD -> Sponsor.Grade.GOLD
    }
}
package kr.loner.core.data.mapper

import kr.loner.core.data.model.ContributorResponse
import kr.loner.core.model.Contributor

internal fun ContributorResponse.toEntity(): Contributor = Contributor(name, imageUrl, githubUrl)
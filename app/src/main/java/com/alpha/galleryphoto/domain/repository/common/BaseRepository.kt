package com.alpha.galleryphoto.domain.repository.common



abstract class BaseRepository {

    companion object {
        // Доступные источники данных, пока что всего 2
        const val TYPE_SOURCE_ALL = 0
        const val TYPE_SOURCE_NETWORK = 2
    }
}


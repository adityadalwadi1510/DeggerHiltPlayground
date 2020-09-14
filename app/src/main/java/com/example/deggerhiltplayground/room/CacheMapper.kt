package com.example.deggerhiltplayground.room

import com.example.deggerhiltplayground.Model.Blog
import com.example.deggerhiltplayground.Util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor():EntityMapper<BlogCacheEntity, Blog>{
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            category = entity.category,
            image = entity.image,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            category = domainModel.category,
            image = domainModel.image,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}
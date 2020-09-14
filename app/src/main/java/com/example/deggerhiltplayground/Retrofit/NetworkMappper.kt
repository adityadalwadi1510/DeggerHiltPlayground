package com.example.deggerhiltplayground.Retrofit

import com.example.deggerhiltplayground.Model.Blog
import com.example.deggerhiltplayground.Util.EntityMapper
import javax.inject.Inject

class NetworkMappper
@Inject
constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            category = entity.category,
            image = entity.image,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            category = domainModel.category,
            image = domainModel.image,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities:List<BlogNetworkEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}
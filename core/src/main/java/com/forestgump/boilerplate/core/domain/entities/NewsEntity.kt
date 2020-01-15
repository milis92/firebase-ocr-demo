package com.forestgump.boilerplate.core.domain.entities

import androidx.room.PrimaryKey
import com.forestgump.boilerplate.core.domain.Entity
import com.forestgump.boilerplate.core.domain.models.News
import com.google.gson.annotations.SerializedName

@androidx.room.Entity(tableName = "news")
internal data class NewsEntity(
        @PrimaryKey
        @SerializedName("token")
        val token: String
) : Entity<News> {

    override fun toModel() = News(token)
}

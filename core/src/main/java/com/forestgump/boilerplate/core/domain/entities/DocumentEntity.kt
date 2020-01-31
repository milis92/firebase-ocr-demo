package com.forestgump.boilerplate.core.domain.entities

import android.net.Uri
import androidx.room.PrimaryKey
import com.forestgump.boilerplate.core.domain.Entity
import com.forestgump.boilerplate.core.domain.models.Document
import com.google.gson.annotations.SerializedName
import java.util.*

@androidx.room.Entity(tableName = "documents")
internal data class DocumentEntity(
        @PrimaryKey
        @SerializedName("id")
        val id: String = UUID.randomUUID().toString(),

        val uri: String,

        val extractedText: String
) : Entity<Document> {

    override fun toModel() = Document(id, extractedText, Uri.parse(uri))
}

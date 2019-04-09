package arthur.com.data.model.source

import arthur.com.data.model.DataModel
import arthur.com.data.model.ResponseModel

data class Sources(override val status: String, override val data: List<Source>) : ResponseModel<Source>

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) : DataModel
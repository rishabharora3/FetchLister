package com.project.network.retrofit

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.project.network.model.GraphQLRequest
import java.lang.reflect.Type


private const val FIELD_QUERY = "query"
private const val FIELD_VARIABLES = "variables"
private const val FIELD_OPERATION = "operationName"

class GraphQLRequestTypeAdapter : JsonSerializer<GraphQLRequest> {

    override fun serialize(
        src: GraphQLRequest,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonObject().apply {
            addProperty(FIELD_QUERY, src.query ?: "")
            src.variables?.let { add(FIELD_VARIABLES, it) }
            src.operation?.let { addProperty(FIELD_OPERATION, it) }
        }
    }
}
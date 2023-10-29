package com.project.network.model

import com.google.gson.Gson
import com.google.gson.JsonElement

class GraphQLRequest {

    var query: String? = null
    var operation: String? = null

    val gson: Gson
        get() = Gson()

    val variables: JsonElement?
        get() = if (_variables.isNotEmpty()) gson.toJsonTree(_variables) else null

    private val _variables: MutableMap<String, Any> = hashMapOf()


    fun setVariable(name: String, value: Any) {
        _variables[name] = value
    }
}
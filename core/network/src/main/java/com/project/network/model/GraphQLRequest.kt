package com.project.network.model

import com.google.gson.Gson
import com.google.gson.JsonElement

open class GraphQLRequest {

    var query: String? = null
    var operation: String? = null

    open val gson: Gson
        get() = Gson()

    open var variables: JsonElement? = null
        get() = if (_variables.isNotEmpty()) gson.toJsonTree(_variables) else null

    private val _variables: MutableMap<String, Any> = hashMapOf()


    open fun setVariable(name: String, value: Any) {
        _variables[name] = value
    }
}
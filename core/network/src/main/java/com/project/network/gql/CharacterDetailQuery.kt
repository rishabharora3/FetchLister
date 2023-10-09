package com.project.network.gql

import com.project.network.model.NetworkCharacterListResponse

internal const val CHARACTER_ID = "id"

/**
 * GraphQL query to obtain [NetworkCharacterListResponse]
 */
internal val CharacterDetailQuery: String = """
    query CharacterDetail(${'$'}$CHARACTER_ID: ID!) {
       character(id: ${'$'}$CHARACTER_ID) {
        id
        image
        name
        location {
          name
          type
          dimension
          residents {
            id
          }
        }
      }
    }
""".trimIndent()
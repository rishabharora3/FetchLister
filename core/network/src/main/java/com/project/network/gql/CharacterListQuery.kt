package com.project.network.gql

import com.project.network.model.NetworkCharacterDetailResponse

internal const val PAGE = "page"

/**
 * GraphQL query to obtain [NetworkCharacterDetailResponse]
 */
internal val CharacterListQuery: String = """
    query getCharacters(${'$'}$PAGE: Int!) {
      characters(page: ${'$'}$PAGE) {
        info {
          count
          pages
          next
          prev
        }
        results {
          id
          name
          status
          species
        }
      }
    }
""".trimIndent()




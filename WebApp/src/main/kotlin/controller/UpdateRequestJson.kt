package controller

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * JSON data class for request to update a task.
 */
data class UpdateRequestJson(
        @JsonProperty("content") val content: String?,
        @JsonProperty("done") val done: Boolean?
)
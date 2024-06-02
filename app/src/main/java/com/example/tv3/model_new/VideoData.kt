package com.example.tv3.model_new

data class VideoData(
    val videoUrl : String? = null,
    val imageUrl : String? = null,
    val title : String? = null,
){
    companion object {
        fun fromMap(map : Map<String, Any>) : VideoData {
            return VideoData(
                videoUrl = map["videoUrl"] as String,
                imageUrl = map["imageUrl"] as String,
                title = map["title"] as String
            )
        }
    }
}
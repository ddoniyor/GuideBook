package com.example.guidebook.domain.model

import com.example.guidebook.data.remote.model.VenueDto

//Should display the name, city, state, and end date, In addition the object’s name, have your view display the image located at each object’s icon url.
data class Guide(
    val endDate: String,
    val icon: String,
    val loginRequired: Boolean,
    val name: String,
    val objType: String,
    val startDate: String,
    val url: String
)

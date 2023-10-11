package com.xevidev.beerapp.listbeers.domain.model

import com.google.gson.annotations.SerializedName
import com.xevidev.beerapp.listbeers.data.model.BeerModel

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
)

fun BeerModel.parseDomain() = Beer(id, name, tagline, firstBrewed, description, imageUrl)
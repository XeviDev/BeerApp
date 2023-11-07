package com.xevidev.beerapp.listbeers.domain.model

import android.os.Parcelable
import com.xevidev.beerapp.listbeers.data.model.BeerModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
    val abv: String
): Parcelable

fun BeerModel.parseDomain() = Beer(id, name, tagline, firstBrewed, description, imageUrl, abv)
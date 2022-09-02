package com.example.seng440assignment1.data.remote.responses

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)
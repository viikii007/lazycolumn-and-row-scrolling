package com.task.task.api.model

data class SubItem(
    val addon_values: List<AddonValue>,
    val id: Int,
    val maximum: Int,
    val minimum: Int,
    val name: String
)
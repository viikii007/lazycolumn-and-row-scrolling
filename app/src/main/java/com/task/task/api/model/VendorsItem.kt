package com.task.task.api.model

data class VendorsItem(
    val items: List<Item>,
    val vendor_category_id: Int,
    val vendor_category_name: String
)
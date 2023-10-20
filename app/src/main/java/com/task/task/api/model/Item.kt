package com.task.task.api.model

data class Item(
    val description: String,
    val duration: String,
    val icon: String,
    val id: Int,
    val is_busy: Int,
    val is_product: Int,
    val name: String,
    val quantity: Int,
    val regular_price: Int,
    val service_discount_price: Any,
    val service_price: Double,
    val sub_item: List<SubItem>,
    val vendor_id: String,
    val working_days: List<WorkingDay>
)
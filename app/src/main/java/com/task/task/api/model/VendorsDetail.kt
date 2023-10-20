package com.task.task.api.model

data class VendorsDetail(
    val address: String,
    val area: String,
    val areas: List<Area>,
    val avg_rating: String,
    val banner: String,
    val delivery_time: String,
    val description: String,
    val destination_id: String,
    val end_time: String,
    val id: String,
    val is_24: String,
    val is_busy: String,
    val is_open: Int,
    val location: String,
    val logo: String,
    val min_order_amount: String,
    val name: String,
    val service_charges: String,
    val start_time: String,
    val total_ratings: String,
    val vendor_categories: List<String>,
    val vendor_policy: String,
    val working_day: String
)
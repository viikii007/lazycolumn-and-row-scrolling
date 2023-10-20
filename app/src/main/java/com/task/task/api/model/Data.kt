package com.task.task.api.model

data class Data(
    val Reviews: List<Review>,
    val User_loyalty_points: Int,
    val Vendors_detail: VendorsDetail,
    val Vendors_items: List<VendorsItem>
)
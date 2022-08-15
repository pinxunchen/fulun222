package com.example.fulun.data

class UserInfo (

    val features: List<Feature>,
    val type:String
)

 class Feature(
    val properties: Properties,
    val type: String
)

class Properties(
    val name:String,
    val user_id:String,
    val address:String,
    val phone:String,
    val amount:String
)
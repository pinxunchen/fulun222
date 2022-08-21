package com.example.fulun.data

import java.io.Serializable

class UserInfo (

    val features: List<Feature>,
    val type:String
):Serializable

 class Feature(
    val properties: Properties,
    val type: String
):Serializable

class Properties(
    val name:String,
    val user_id:String,
    val address:String,
    val phone:String,
    val amount:String,
    val identity:String

):Serializable
package com.example.fulun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fulun.data.UserInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUserData()
    }






    private fun getUserData() {
//        okhttp設定
        val userdataUrl="https://raw.githubusercontent.com/Pinnnnnnnn/crwon/main/user_data.json"
        val okHttpClient = OkHttpClient().newBuilder().build()
        val request =  Request.Builder().url(userdataUrl).get().build()
        val call = okHttpClient.newCall(request)

        call.enqueue(object  :Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("Pin","onFailure $e")
            }


            override fun onResponse(call: okhttp3.Call , response: Response) {
                val userData = response.body?.string()
//              userData拿資料後轉gson          (資料來源,轉換成xx格式)
                val userInfo = Gson().fromJson(userData,UserInfo::class.java)

                for(i in userInfo.features){
                    Log.d("pin","個案姓名: ${i.properties.name},個案手機: ${i.properties.phone}")
                }


            }
        })
    }
 }
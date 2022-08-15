package com.example.fulun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.fulun.data.UserInfo
import com.example.fulun.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserData()
    }






    private fun getUserData() {
        binding.progressBar.visibility= View.VISIBLE

//        okhttp設定
        val userdataUrl="https://raw.githubusercontent.com/Pinnnnnnnn/crwon/main/user_data.json"
        val okHttpClient = OkHttpClient().newBuilder().build()
        val request =  Request.Builder().url(userdataUrl).get().build()
        val call = okHttpClient.newCall(request)

        call.enqueue(object  :Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("Pin","onFailure $e")

            runOnUiThread {
                binding.progressBar.visibility= View.GONE
            }
            }


            override fun onResponse(call: okhttp3.Call , response: Response) {
                var propertiesName = StringBuilder()
                val userData = response.body?.string()
//              userData拿資料後轉gson          (資料來源,轉換成xx格式)
                val userInfo = Gson().fromJson(userData,UserInfo::class.java)

                for(i in userInfo.features){
                    propertiesName.append(i.properties.name + "     "+i.properties.address+"\n")
                }

                runOnUiThread {
                    binding.tvUserData.text = propertiesName
                    binding.progressBar.visibility = View.GONE

                }


            }
        })
    }
 }
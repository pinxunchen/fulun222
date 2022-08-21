package com.example.fulun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulun.data.Feature
import com.example.fulun.data.UserInfo
import com.example.fulun.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(),MainAdapter.IItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewManager:RecyclerView.LayoutManager
    private lateinit var viewAdapter:MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        getUserData()
    }







    private fun initView() {
         viewManager = LinearLayoutManager(this)
         viewAdapter = MainAdapter(this)

        binding.recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter


        }
    }


    private fun getUserData() {
        binding.progressBar.visibility= View.VISIBLE

//        okhttp設定
        val userdataUrl="https://raw.githubusercontent.com/pinxunchen/crwon/main/user_data.json"
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
//                var propertiesName = StringBuilder()
                val userData = response.body?.string()
//              userData拿資料後轉gson          (資料來源,轉換成xx格式)
                val userInfo = Gson().fromJson(userData,UserInfo::class.java)

                runOnUiThread {
                    viewAdapter.userList = userInfo.features
                    binding.progressBar.visibility = View.GONE
                }

            }
        })
    }

    override fun onItemClickListener(data: Feature) {
        val intent = Intent(this,UserDetailMainActivity::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }
}
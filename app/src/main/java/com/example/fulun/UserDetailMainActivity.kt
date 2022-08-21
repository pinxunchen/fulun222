package com.example.fulun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fulun.data.Feature
import com.example.fulun.databinding.ActivityUserDetailMainBinding

class UserDetailMainActivity : AppCompatActivity() {
    private val  data by lazy{intent.getSerializableExtra("data") as Feature}
    private val name by lazy { data?.properties?.name }
    private val user_id by lazy { data?.properties?.user_id }
    private val identity by lazy { data?.properties?.identity }
    private val address by lazy { data?.properties?.address }
    private val phone by lazy { data?.properties?.phone }
    private val amount by lazy { data?.properties?.amount }


    private  lateinit var  binding: ActivityUserDetailMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }







    private fun initView() {
        binding.tvName.text = name
        binding.tvIdentity.text = identity
        binding.tvId.text = user_id
        binding.tvAddress.text = address
        binding.tvPhone.text = phone
        binding.tvAmount.text = amount
    }
}
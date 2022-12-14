package com.example.fulun

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fulun.data.Feature
import com.example.fulun.databinding.ItemViewBinding

class MainAdapter(private  val iItemClickListener: IItemClickListener):
    RecyclerView.Adapter<MainAdapter.MyViewHolder>(){

    var userList:List<Feature> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }




    class MyViewHolder (val itemViewBinding:ItemViewBinding):
        RecyclerView.ViewHolder(itemViewBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return  MyViewHolder(itemViewBinding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        要拿的資料
        holder.itemViewBinding.tvName.text = userList[position].properties.name
        holder.itemViewBinding.tvUseId.text = userList[position].properties.user_id


        holder.itemViewBinding.layoutItem.setOnClickListener {
            iItemClickListener.onItemClickListener(userList[position])
        }

    }
    override fun getItemCount(): Int {
        return userList.size
    }

    interface IItemClickListener{
        fun onItemClickListener(data:Feature)

    }






}

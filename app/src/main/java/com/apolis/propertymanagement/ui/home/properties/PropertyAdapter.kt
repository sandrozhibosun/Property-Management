package com.apolis.propertymanagement.ui.home.properties

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.data.models.Property
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_adapter_property.view.*

class PropertyAdapter (var mContext:Context,var mList:ArrayList<Property> = ArrayList()):
        RecyclerView.Adapter<PropertyAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(property: Property){
            Picasso.get()
                .load(property.image)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.display_property)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_adapter_property, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var property=mList[position]
        holder.bind(property)
    }
    fun setData(propertyList:ArrayList<Property>){
        mList=propertyList
        notifyDataSetChanged()
    }

}
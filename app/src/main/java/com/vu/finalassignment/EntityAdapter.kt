package com.vu.finalassignment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntityAdapter(private val entities: List<Entity>) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val property1TextView: TextView = itemView.findViewById(R.id.entity_property1)
        val property2TextView: TextView = itemView.findViewById(R.id.entity_property2)
        // Add more views for other properties if needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.entity_item, parent, false) // Use your item layout
        return EntityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.property1TextView.text = entity.property1
        holder.property2TextView.text = entity.property2
        // Set values for other views if needed

        holder.itemView.setOnClickListener {
            // Handle item click, e.g., start DetailsActivity
            val context = holder.itemView.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("entity", entity)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}
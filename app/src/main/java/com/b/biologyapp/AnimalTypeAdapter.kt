package com.b.biologyapp

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

class AnimalTypeAdapter(private val dataSet: List<AnimalType>) :
    RecyclerView.Adapter<AnimalTypeAdapter.ViewHolder>() {
    private val animalTypesList = dataSet
    var onItemClick: ((AnimalType) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvInterestingFact = itemView.findViewById<TextView>(R.id.tvInterestingFact)
        val imv = itemView.findViewById<ImageView>(R.id.imv)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(animalTypesList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.animal_type_item, parent, false
        )
        return ViewHolder(itemView) //bhf [fht,jdf
    }

    override fun getItemCount(): Int {
        return animalTypesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animalType = animalTypesList[position] //youtube

        holder.tvName.text = animalType.name
        holder.tvInterestingFact.text = animalType.interestingFact
        try {
            val ims = holder.imv.context.assets.open(animalType.image)  //
            val d = Drawable.createFromStream(ims, null)  //
            holder.imv.setImageDrawable(d)
            ims.close()
        } catch (ex: IOException) {
            return
        }
    }
}
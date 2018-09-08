package de.thinkad.lumoo.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.thinkad.lumoo.Interest
import de.thinkad.lumoo.R
import de.thinkad.lumoo.inflate

/**
 * Created by andreas on 11.06.2018.
 */
class InterestsAdapter(
        val context: Context,
        val objects: List<Interest>,
        val update: () -> Unit
) : RecyclerView.Adapter<InterestViewHolder>() {


    override fun onBindViewHolder(holder: InterestViewHolder, position: Int)
            = holder.bind(
            context = context,
            item = objects[position],
            updateList = update)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestViewHolder {
        return InterestViewHolder(itemView = parent.inflate(R.layout.display_simple_element))
    }

    override fun getItemCount(): Int = objects.size
}
package de.thinkad.lumoo.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.thinkad.lumoo.R
import de.thinkad.lumoo.User
import de.thinkad.lumoo.inflate

/**
 * Created by andreas on 11.06.2018.
 */
class DiscoverAdapter(
        val context: Context,
        val objects: List<User>,
        val onClick: (user:User) -> Unit
) : RecyclerView.Adapter<DiscoverViewHolder>() {


    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        var dis = true
        holder.bind(
                context = context,
                item = objects[position],
                onUserDelivered = onClick)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        return DiscoverViewHolder(itemView = parent.inflate(R.layout.display_discover))
    }

    override fun getItemCount(): Int = objects.size
}
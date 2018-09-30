package de.thinkad.lumoo.business.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.thinkad.lumoo.data.Event
import de.thinkad.lumoo.R
import de.thinkad.lumoo.inflate

/**
 * Created by andreas on 11.06.2018.
 */
class EventAdapter(
        val context: Context,
        val objects: List<Event>,
        val update: () -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {


    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        var dis = true
        if (position > 0) {
            dis = objects[position - 1].date.month != objects[position].date.month
        }
        holder.bind(
                context = context,
                item = objects[position],
                updateList = update,
                display_month =dis)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(itemView = parent.inflate(R.layout.display_event))
    }

    override fun getItemCount(): Int = objects.size
}
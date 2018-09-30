package de.thinkad.lumoo.business.modeladapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import de.thinkad.lumoo.data.Event
import de.thinkad.lumoo.application.dialogs.EventActivity
import de.thinkad.lumoo.format
import kotlinx.android.synthetic.main.display_event.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by andreas on 11.06.2018.
 */
class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
            context: Context,
            item: Event,
            updateList: () -> Unit,
            display_month: Boolean
    ) = with(itemView) {
        img_event.setImageDrawable(context.getDrawable(item.image.uri))
        txt_name.text = item.title
        lbl_date.text = item.date.format()
        lbl_month.text = item.date.format("MMMM")
        lbl_month.visibility = if (display_month) VISIBLE else GONE
        onClick {
            val intent = Intent(context, EventActivity::class.java)
            intent.putExtra("event", item)
            context.startActivity(intent)
        }
    }
}
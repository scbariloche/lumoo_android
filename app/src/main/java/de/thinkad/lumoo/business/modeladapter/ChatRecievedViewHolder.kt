package de.thinkad.lumoo.business.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import de.thinkad.lumoo.data.Chat
import de.thinkad.lumoo.format
import kotlinx.android.synthetic.main.display_chat_recieved.view.*

/**
 * Created by andreas on 11.06.2018.
 */
class ChatRecievedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
            context: Context,
            item: Chat,
            function: () -> Unit,
            display_day:Boolean
    ) = with(itemView) {

        lbl_chat_recieved.text = item.message

        txt_chat_recieved_date.text=item.timestamp.format("dd.MM.yyyy")
        txt_chat_recieved_date.visibility= if (display_day) VISIBLE else GONE
        txt_chat_recieved_time.text=item.timestamp.format("HH:mm")
    }
}
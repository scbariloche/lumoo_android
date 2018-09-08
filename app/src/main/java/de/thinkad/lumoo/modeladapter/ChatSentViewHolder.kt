package de.thinkad.lumoo.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import de.thinkad.lumoo.Chat
import de.thinkad.lumoo.Interest
import de.thinkad.lumoo.format
import kotlinx.android.synthetic.main.display_chat_sent.view.*

/**
 * Created by andreas on 11.06.2018.
 */
class ChatSentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
            context: Context,
            item: Chat,
            function: () -> Unit,
            display_day:Boolean
    ) = with(itemView) {

        lbl_chat_sent.text=item.message

        txt_chat_sent_date.text=item.timestamp.format("dd.MM.yyyy")
        txt_chat_sent_date.visibility= if (display_day) View.VISIBLE else View.GONE
        txt_chat_sent_time.text=item.timestamp.format("HH:mm")
    }
}
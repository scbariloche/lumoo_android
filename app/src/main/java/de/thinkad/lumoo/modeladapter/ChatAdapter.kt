package de.thinkad.lumoo.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.thinkad.lumoo.Chat
import de.thinkad.lumoo.R
import de.thinkad.lumoo.inflate

/**
 * Created by andreas on 11.06.2018.
 */
class ChatAdapter(
        val context: Context,
        val objects: List<Chat>,
        val function: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var dis = true
        if (position <objects.size-1) {
            dis = objects[position+1].timestamp.day != objects[position].timestamp.day
        }
        when (holder.itemViewType) {
            0 -> {
                val ho = holder as ChatSentViewHolder
                ho.bind(
                        context = context,
                        item = objects[position],
                        function = function,
                        display_day = dis)
            }
            2 -> {
                val ho = holder as ChatRecievedViewHolder
                ho.bind(
                        context = context,
                        item = objects[position],
                        function = function,
                        display_day=dis)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            0 -> return ChatSentViewHolder(itemView = parent.inflate(R.layout.display_chat_sent))
            2 -> return ChatRecievedViewHolder(itemView = parent.inflate(R.layout.display_chat_recieved))
            else -> return ChatRecievedViewHolder(itemView = parent.inflate(R.layout.display_chat_recieved))
        }


    }

    override fun getItemCount(): Int = objects.size

    override fun getItemViewType(position: Int): Int {

        if (objects[position].sent) {
            return 0
        } else {
            return 2
        }

    }


}
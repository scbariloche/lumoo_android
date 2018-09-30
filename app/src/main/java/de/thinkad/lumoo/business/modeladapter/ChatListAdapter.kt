package de.thinkad.lumoo.business.modeladapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.thinkad.lumoo.R
import de.thinkad.lumoo.data.User
import de.thinkad.lumoo.inflate
import de.thinkad.lumoo.data.test_chats

/**
 * Created by andreas on 11.06.2018.
 */
class ChatListAdapter(
        val context: Activity,
        val objects: List<User>,
        val function: (user: User) -> Unit,
        val update: () -> Unit
) : RecyclerView.Adapter<ChatListViewHolder>() {


    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        val item = objects[position]
        item.chats = test_chats[item.id.toInt()]
        holder.bind(
                context = context,
                item = objects[position],
                function = function, update = update
        )
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        return ChatListViewHolder(itemView = parent.inflate(R.layout.display_chatlist_element))
    }

    override fun getItemCount(): Int = objects.size
}
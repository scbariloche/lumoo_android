package de.thinkad.lumoo.modeladapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import de.thinkad.lumoo.User
import kotlinx.android.synthetic.main.display_discover.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by andreas on 11.06.2018.
 */
class DiscoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
            context: Context,
            item: User,
            onUserDelivered: (user: User) -> Unit
    ) = with(itemView) {

        img_profile.setImageDrawable(context.getDrawable(item.profilepic.uri))
        onClick { onUserDelivered(item) }
        txt_name.text = "${item.first_name} ${item.name}"
        lbl_distance.text = "${item.distance}km"

        list_interests.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list_interests.adapter = InterestsAdapter(context, item.interests, { })



        img_spontaneous.visibility= if(item!!.spontaneous) View.VISIBLE else View.INVISIBLE
        img_taken.visibility= if(item!!.taken) View.VISIBLE else View.INVISIBLE

    }
}
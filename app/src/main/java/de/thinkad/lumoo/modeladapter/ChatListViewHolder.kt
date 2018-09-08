package de.thinkad.lumoo.modeladapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import de.thinkad.lumoo.User
import de.thinkad.lumoo.createAlert
import de.thinkad.lumoo.dialogs.NewEventActivity
import kotlinx.android.synthetic.main.display_chatlist_element.view.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by andreas on 11.06.2018.
 */
class ChatListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
            context: Activity,
            item: User,
            function: (user: User) -> Unit,
            update: () -> Unit
    ) = with(itemView) {
        chat_firstname.text = item.first_name
        chat_lastname.text = item.name
        chat_teaser.text = item.chats.last().message
        chat_profilepic.setImageDrawable(context.getDrawable(item.profilepic.uri))
        itemView.onClick { function(item) }

        if (item.trusted) {
            btn_cal.visibility = VISIBLE
            btn_tel.visibility = VISIBLE
            btn_trust.visibility = GONE
        } else {
            btn_cal.visibility = GONE
            btn_tel.visibility = GONE
            btn_trust.visibility = VISIBLE
        }
        btn_trust.onClick {
            createAlert(
                    context = context,
                    message = "Möchtest du ${item.first_name} ${item.name} vertrauen?",
                    yesAction = {
                        item.trusted = true
                        update()
                    },
                    noAction = {}
            )
        }
        btn_tel.onClick {
            context.longToast("${item.first_name} ${item.name} wird angrufen")
        }
        btn_cal.onClick {
            context.startActivityForResult(Intent(context, NewEventActivity::class.java), 1)
        }
    }
}
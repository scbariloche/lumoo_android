package de.thinkad.lumoo.application.tabbar_fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import de.thinkad.lumoo.*
import de.thinkad.lumoo.application.ChatActivity
import de.thinkad.lumoo.application.dialogs.ProfileActivity
import de.thinkad.lumoo.business.interfaces.Filterable

import de.thinkad.lumoo.business.modeladapter.ChatListAdapter
import de.thinkad.lumoo.data.User
import de.thinkad.lumoo.data.req_chat
import de.thinkad.lumoo.data.res_profile
import de.thinkad.lumoo.data.test_users
import kotlinx.android.synthetic.main.fragment_chat_list.view.*
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper.Callback.makeMovementFlags
import android.support.v7.widget.helper.ItemTouchHelper.LEFT
import android.support.v7.widget.helper.ItemTouchHelper.RIGHT


/**
 * A simple [Fragment] subclass.
 */


class ChatListFragment : Fragment() {
    var list = mutableListOf<Filterable>()
    var contentview: View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        contentview = inflater!!.inflate(R.layout.fragment_chat_list, container, false)
        list = test_users as MutableList<Filterable>
        return contentview

    }

    override fun onResume() {
        super.onResume()

        update()
    }

    fun update() {
        val swiper = SwipeController()
        val itemTouchhelper = ItemTouchHelper(swiper)
        itemTouchhelper.attachToRecyclerView(contentview!!.listview_chatslist)

        contentview!!.listview_chatslist.layoutManager = LinearLayoutManager(activity, VERTICAL, false)
        contentview!!.listview_chatslist.adapter = ChatListAdapter(
                context = activity,
                objects = list as List<User>,
                function = {
                    val intent = Intent(activity, ChatActivity::class.java)
                    intent.putExtra("user", it)
                    startActivityForResult(Intent(intent), req_chat)
                },
                update = {
                    update()
                })

        contentview!!.txt_chat_filter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                list = (test_users as MutableList<Filterable>).filter("$s")
                update()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            res_profile -> {
                val intent = Intent(activity, ProfileActivity::class.java)
                intent.putExtra("user", data!!.getSerializableExtra("user"))
                activity.startActivityForResult(intent, req_chat)
            }
        }
    }

    internal inner class SwipeController : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return makeMovementFlags(0, LEFT or RIGHT)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }
    }

}// Required empty public constructor

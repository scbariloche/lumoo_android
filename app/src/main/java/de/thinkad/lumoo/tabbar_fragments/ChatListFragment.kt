package de.thinkad.lumoo.tabbar_fragments


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
import de.thinkad.lumoo.dialogs.ProfileActivity
import de.thinkad.lumoo.interfaces.Filterable

import de.thinkad.lumoo.modeladapter.ChatListAdapter
import kotlinx.android.synthetic.main.fragment_chat_list.view.*


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
}// Required empty public constructor

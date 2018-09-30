package de.thinkad.lumoo.application.tabbar_fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import de.thinkad.lumoo.*
import de.thinkad.lumoo.application.ChatActivity

import de.thinkad.lumoo.application.dialogs.ProfileActivity
import de.thinkad.lumoo.business.modeladapter.DiscoverAdapter
import de.thinkad.lumoo.data.req_chat
import de.thinkad.lumoo.data.req_profile
import de.thinkad.lumoo.data.res_chat
import de.thinkad.lumoo.data.test_users
import kotlinx.android.synthetic.main.fragment_discover.view.*


/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : Fragment() {

    var contentview: View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        contentview = inflater!!.inflate(R.layout.fragment_discover, container, false)
        init_components()
        return contentview
    }

    private fun init_components() {
        contentview!!.list_discover.layoutManager = LinearLayoutManager(activity, VERTICAL, false)


        contentview!!.list_discover.adapter = DiscoverAdapter(activity, test_users, {
            val intent = Intent(activity, ProfileActivity::class.java)
            intent.putExtra("user", it)
            startActivityForResult(intent, req_profile)

        })
        val helper = LinearSnapHelper()
        helper.attachToRecyclerView(contentview!!.list_discover)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        when (resultCode) {
            res_chat -> {
                val intent = Intent(activity, ChatActivity::class.java)
                intent.putExtra("user", data!!.getSerializableExtra("user"))
                activity.startActivityForResult(intent, req_chat)
            }
        }
    }
}// Required empty public constructor

package de.thinkad.lumoo.tabbar_fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import de.thinkad.lumoo.R
import de.thinkad.lumoo.dialogs.FilterEventActivity
import de.thinkad.lumoo.dialogs.NewEventActivity
import de.thinkad.lumoo.modeladapter.EventAdapter
import de.thinkad.lumoo.test_events
import kotlinx.android.synthetic.main.fragment_event.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class EventFragment : Fragment() {
    var contentview: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        contentview = inflater!!.inflate(R.layout.fragment_event, container, false)
        init_components()
        return contentview

    }


    fun init_components() {
        contentview!!.list_events.layoutManager = LinearLayoutManager(activity, VERTICAL, false)
        contentview!!.list_events.adapter = EventAdapter(activity, test_events, { })
        contentview!!.btn_new_event.onClick {
            startActivityForResult(Intent(activity, NewEventActivity::class.java), 10)
        }
        contentview!!.btn_filter_events.onClick {
            startActivityForResult(Intent(activity, FilterEventActivity::class.java), 10)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        init_components()
    }
}// Required empty public constructor

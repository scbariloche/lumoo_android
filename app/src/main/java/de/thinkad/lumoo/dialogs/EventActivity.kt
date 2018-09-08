package de.thinkad.lumoo.dialogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.thinkad.lumoo.Event
import de.thinkad.lumoo.R
import de.thinkad.lumoo.format
import de.thinkad.lumoo.test_events
import kotlinx.android.synthetic.main.dialog_event.*

class EventActivity : AppCompatActivity() {
    var event: Event? = test_events[0]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_event)
        event=intent.getSerializableExtra("event") as Event
        init_components()


    }

    private fun init_components() {
        imageView.setImageDrawable(getDrawable(event!!.image.uri))
        lbl_title.text = event!!.title
        txt_date.text = event!!.date.format()
        txt_time.text = "${event!!.date.format("HH:mm")} Uhr"
        txt_place.text = event!!.place.toString()
        txt_description.text=event!!.description
    }
}

package de.thinkad.lumoo.dialogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.thinkad.lumoo.Event
import de.thinkad.lumoo.R
import de.thinkad.lumoo.test_events
import de.thinkad.lumoo.test_users
import kotlinx.android.synthetic.main.dialog_new_event.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

class FilterEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_filter)

    }
}

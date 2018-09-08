package de.thinkad.lumoo.dialogs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.thinkad.lumoo.*
import kotlinx.android.synthetic.main.dialog_new_event.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

class NewEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_new_event)
        btn_save.onClick {
            test_events.add(Event(
                    15,
                    edit_title.text.toString(),
                    edit_description.text.toString(),
                    Date(System.currentTimeMillis()),
                    23,
                    true,
                    0,
                    true,
                    host = test_users[0],
                    image = Image(id = 0, uri = R.mipmap.ic_launcher_round),
                    participants = mutableListOf()
            ))
            finish()
        }
        btn_cancel.onClick {
            finish()
        }
    }
}

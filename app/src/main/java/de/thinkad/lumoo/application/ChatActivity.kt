package de.thinkad.lumoo.application

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.LinearLayout.VERTICAL
import de.thinkad.lumoo.*
import de.thinkad.lumoo.application.dialogs.ProfileActivity
import de.thinkad.lumoo.business.modeladapter.ChatAdapter
import de.thinkad.lumoo.data.Chat
import de.thinkad.lumoo.data.User
import de.thinkad.lumoo.data.test_chats
import de.thinkad.lumoo.data.test_users
import kotlinx.android.synthetic.main.activity_chat.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*


class ChatActivity : AppCompatActivity() {
    var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        user = intent.getSerializableExtra("user") as User
        setSupportActionBar(findViewById(R.id.chat_toolbar))

        chat_list.layoutManager = LinearLayoutManager(this, VERTICAL, true) as RecyclerView.LayoutManager?
        chat_list.adapter = ChatAdapter(this, test_chats[user!!.id.toInt()].reversed(), {})


        supportActionBar!!.title = "Chat mit ${user!!.first_name} ${user!!.name}"
        chat_toolbar.onClick {
            val intent = Intent(this@ChatActivity, ProfileActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        btn_send_chat.onClick {
            test_chats[user!!.id.toInt()].add(Chat(test_users[0], edittext_chat.text.toString(), Date(System.currentTimeMillis()), true))
            chat_list.adapter = ChatAdapter(this@ChatActivity, test_chats[user!!.id.toInt()].reversed(), {})
            edittext_chat.text.clear()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}


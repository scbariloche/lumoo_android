package de.thinkad.lumoo.dialogs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import de.thinkad.lumoo.R
import de.thinkad.lumoo.modeladapter.InterestsAdapter
import de.thinkad.lumoo.test_user
import kotlinx.android.synthetic.main.dialog_edit_profile.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class EditProfileActivity : AppCompatActivity() {
    var user = test_user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_edit_profile)
        init_components()

    }


    private fun init_components() {
        img_profile.setImageDrawable(getDrawable(user.profilepic.uri))
        edit_first_name.setText("${user.first_name}")
        edit_name.setText("${user.name}")
        edit_about.setText(user.about)
        edit_what.setText(user.what)
        list_interests.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        list_interests.adapter = InterestsAdapter(this, user.interests, { })
        switch_spontaneous.isChecked = user.spontaneous
        switch_taken.isChecked = user.taken
        btn_save.onClick {

            test_user.first_name = edit_first_name.text.toString()
            test_user.name=edit_name.text.toString()
            test_user.taken=switch_taken.isChecked
            test_user.spontaneous=switch_spontaneous.isChecked
            test_user.about=edit_about.text.toString()
            test_user.what=edit_what.text.toString()
            finish()


        }
    }
}

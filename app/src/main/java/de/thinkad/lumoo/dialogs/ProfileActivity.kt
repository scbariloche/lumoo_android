package de.thinkad.lumoo.dialogs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import android.support.v7.widget.StaggeredGridLayoutManager
import de.thinkad.lumoo.*
import de.thinkad.lumoo.modeladapter.GalleryAdapter
import de.thinkad.lumoo.modeladapter.InterestsAdapter
import kotlinx.android.synthetic.main.dialog_profile.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import de.thinkad.lumoo.R.id.photos_viewpager
import android.support.v4.view.ViewPager
import android.view.View.*
import de.thinkad.lumoo.modeladapter.PhotosAdapter


class ProfileActivity : AppCompatActivity() {
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_profile)

        user = intent.getSerializableExtra("user") as User

        img_profile.setImageDrawable(this.getDrawable(user!!.profilepic.uri))
        txt_name.text = "${user!!.first_name} ${user!!.name}"
        list_interests.layoutManager =
                StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        list_interests.adapter = InterestsAdapter(this, user!!.interests, { })

        list_images.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        list_images.adapter = GalleryAdapter(this, test_images, {})

        img_spontaneous.visibility = if (user!!.spontaneous) VISIBLE else INVISIBLE
        img_taken.visibility = if (user!!.taken) VISIBLE else INVISIBLE

        txt_about.text = user!!.about
        txt_what.text = user!!.what
        btn_chat.onClick {
            val intent = Intent(this@ProfileActivity, ChatActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
        photos_viewpager.visibility = GONE

//        val adapter = PhotosAdapter(this, test_images)
//        photos_viewpager.adapter = adapter
//
//        tab_layout.setupWithViewPager(photos_viewpager, true)

    }
}

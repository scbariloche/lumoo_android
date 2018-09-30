package de.thinkad.lumoo.application.tabbar_fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import de.thinkad.lumoo.R
import de.thinkad.lumoo.application.dialogs.EditProfileActivity
import de.thinkad.lumoo.business.modeladapter.InterestsAdapter
import de.thinkad.lumoo.data.test_user
import kotlinx.android.synthetic.main.fragment_personal.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * A simple [Fragment] subclass.
 */
class PersonalFragment : Fragment() {
    var user = test_user
    var contentview: View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        contentview = inflater!!.inflate(R.layout.fragment_personal, container, false)



        init_components()
        return contentview
    }

    private fun init_components() {
        contentview!!.img_profile.setImageDrawable(activity.getDrawable(user.profilepic.uri))
        contentview!!.txt_name.text = "${user.first_name} ${user.name}"
        contentview!!.txt_about.text = user.about
        contentview!!.txt_what.text = user.what
        contentview!!.list_interests.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        contentview!!.list_interests.adapter = InterestsAdapter(activity, user.interests, { })
        contentview!!.btn_edit.onClick {
            startActivityForResult(Intent(activity, EditProfileActivity::class.java), 1)
        }
        contentview!!.img_spontaneous.visibility = if (user!!.spontaneous) VISIBLE else INVISIBLE
        contentview!!.img_taken.visibility = if (user!!.taken) VISIBLE else INVISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        user = test_user
        init_components()
    }
}

package de.thinkad.lumoo.application.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.thinkad.lumoo.R
import de.thinkad.lumoo.application.MainActivity
import de.thinkad.lumoo.business.services.MySharedPreferences
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.toast

class WelcomeActivity : AppCompatActivity() {

    lateinit var layouts: MutableList<Int>
    private var prefManager: MySharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Checking for first time launch - before calling setContentView()
        prefManager = MySharedPreferences(this)
        if (!prefManager!!.isFirstTimeLaunch()) {
//            launchHomeScreen()
//            finish()
        }

        setContentView(R.layout.activity_welcome)


        // layouts of all welcome sliders
        layouts = mutableListOf(R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4,
                R.layout.welcome_slide5)

        // adding bottom dots
        addBottomDots(0)


        view_pager.adapter = MyViewPagerAdapter()
        view_pager.addOnPageChangeListener(MyPageChangelistener())

        btn_login!!.setOnClickListener {
            launchHomeScreen()
        }

        btn_register!!.setOnClickListener {
            toast("registrieren noch nicht möglich")
            // checking for last page
            // if last page home screen will be launched
//            val current = getItem(+1)
//            if (current < layouts!!.size) {
//                // move to next screen
//                viewPager!!.currentItem = current
//            } else {
//                launchHomeScreen()
//            }
        }
    }

    private fun addBottomDots(currentPage: Int) {
       val dots = mutableListOf<TextView>()


        layoutDots.removeAllViews()
        for (i in 0..layouts.size - 1) {
            print(i)
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#8226;")
            dots[i].textSize = 35f
            dots[i].setTextColor(resources.getColor(R.color.colorLight))
            layoutDots.addView(dots[i])
        }

        if (dots.size > 0)
            dots[currentPage].setTextColor(resources.getColor(R.color.colorPrimaryDark))
    }


    private fun launchHomeScreen() {
        prefManager!!.setFirstTimeLaunch(false)
        startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        finish()
    }

    inner class MyPageChangelistener: ViewPager.OnPageChangeListener{

        override fun onPageSelected(position: Int) {
            addBottomDots(position)
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }

    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = layoutInflater.inflate(layouts[position], container, false)
            container.addView(view)

            return view
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }


        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View)
        }
    }
}
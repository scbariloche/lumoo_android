package de.thinkad.lumoo.business.modeladapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import de.thinkad.lumoo.data.Image
import de.thinkad.lumoo.R
import kotlinx.android.synthetic.main.display_gallery_element.view.*
import org.jetbrains.anko.layoutInflater
import android.support.v4.view.ViewPager




/**
 * Created by andreas on 26.06.2018.
 */
class PhotosAdapter(context:Context, list: MutableList<Image>) : PagerAdapter() {
    val context=context
    val list =list
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view === `object` as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val page = context.layoutInflater.inflate(R.layout.display_gallery_element, container, false)

//        var position = position * 2


//        position++

//        if (position < getRealCount()) {
//            populateTextView(tv, position)
//            tv.visibility = View.VISIBLE
//        } else {
//            tv.visibility = View.INVISIBLE
//        }
        val item = list[position]
        page.imgView.setImageDrawable(context.getDrawable(item.uri))
        container!!.addView(page)

        return page
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.9f
    }
}
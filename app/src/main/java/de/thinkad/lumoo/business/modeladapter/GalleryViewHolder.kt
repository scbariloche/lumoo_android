package de.thinkad.lumoo.business.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import de.thinkad.lumoo.data.Image
import kotlinx.android.synthetic.main.display_gallery_element.view.*

/**
 * Created by andreas on 11.06.2018.
 */
class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
            context: Context,
            item: Image,
            updateList: () -> Unit
    ) = with(itemView) {

        imgView.setImageDrawable(context.getDrawable(item.uri))
    }
}
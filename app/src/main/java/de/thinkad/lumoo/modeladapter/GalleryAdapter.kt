package de.thinkad.lumoo.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.thinkad.lumoo.Image
import de.thinkad.lumoo.R
import de.thinkad.lumoo.inflate

/**
 * Created by andreas on 11.06.2018.
 */
class GalleryAdapter(
        val context: Context,
        val objects: List<Image>,
        val update: () -> Unit
) : RecyclerView.Adapter<GalleryViewHolder>() {


    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int)
            = holder.bind(
            context = context,
            item = objects[position],
            updateList = update)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(itemView = parent.inflate(R.layout.display_gallery_element))
    }

    override fun getItemCount(): Int = objects.size
}
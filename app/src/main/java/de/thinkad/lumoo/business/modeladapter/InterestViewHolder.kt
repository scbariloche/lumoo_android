package de.thinkad.lumoo.business.modeladapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import de.thinkad.lumoo.data.Interest
import kotlinx.android.synthetic.main.display_simple_element.view.*

/**
 * Created by andreas on 11.06.2018.
 */
class InterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
            context: Context,
            item: Interest,
            updateList: () -> Unit
    ) = with(itemView) {

        lbl_interest.text=item.name
    }
}
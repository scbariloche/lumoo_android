package de.thinkad.lumoo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.thinkad.lumoo.business.interfaces.Filterable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by andreas on 11.06.2018.
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun Date.format(pattern: String = "dd.MM.yyyy"): String {
    val formatter = SimpleDateFormat(pattern)
    return formatter.format(this)
}

fun String.teaser(length: Int): String {

    if (this.length >= length) {
        return this.substring(0, length) + "..."
    } else {
        return this
    }
}

fun MutableList<Filterable>.filter(pattern: String): MutableList<Filterable> {
    var retlist = mutableListOf<Filterable>()
    for (f in this) {
        if (f.filterableString().toLowerCase().contains(pattern.toLowerCase())) {
            retlist.add(f)
        }
    }
    return retlist
}
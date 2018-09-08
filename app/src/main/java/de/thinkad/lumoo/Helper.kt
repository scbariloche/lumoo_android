package de.thinkad.lumoo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.databinding.adapters.TextViewBindingAdapter.setTextSize
import android.view.View
import android.widget.TextView



/**
 * Created by andenk on 25.06.2018.
 */
fun createAlert(context: Context, message: String, yesAction: () -> Unit, noAction: () -> Unit) {
    val yesAction = object : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            yesAction()
        }
    }
    val noAction = object : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            noAction()
        }
    }

    val dialog = createAlert(context, message).setPositiveButton(android.R.string.yes, yesAction).setNegativeButton(android.R.string.cancel, noAction).show()
    val textView = dialog.findViewById<View>(android.R.id.message) as TextView
    textView.textSize = 24f


}


fun createAlert(context: Context, message: String): AlertDialog.Builder {
    val builder: AlertDialog.Builder
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        builder = AlertDialog.Builder(context, R.style.lumoo_alert_dialog)
    } else {
        builder = AlertDialog.Builder(context)
    }
    builder.setMessage(message)
            .setPositiveButton(android.R.string.yes) { _, _ -> }



    return builder
}
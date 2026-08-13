package com.example.todoapproom.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.todoapproom.R

class AddEditDialog(
    context: Context,
    private val oldText: String = "",
    private val onSave: (String) -> Unit
) : Dialog(context) {

    private lateinit var editText: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_edit)

        window?.setLayout(
            (context.resources.displayMetrics.widthPixels * 0.9).toInt(), // 90% of screen width
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        // Optional: set background to transparent so your rounded corners work
        window?.setBackgroundDrawableResource(android.R.color.transparent)


        editText = findViewById(R.id.editText)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        editText.setText(oldText)

        btnSave.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                onSave(text)
                dismiss()
            }
        }

        btnCancel.setOnClickListener { dismiss() }
    }
}

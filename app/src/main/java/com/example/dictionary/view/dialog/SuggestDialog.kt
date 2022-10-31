package org.legobyte.khanedan.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import com.example.dictionary.R
import kotlinx.android.synthetic.main.activity_suggestion_dialog.*
import java.io.File

class SuggestDialog(context: Context) : Dialog(context){

    private val edt_title by lazy { findViewById<EditText>(R.id.edt_titleDialogSuggest) }
    private val edt_desc by lazy { findViewById<EditText>(R.id.edt_descDialogSuggest) }
    private val edt_phone by lazy { findViewById<EditText>(R.id.edt_phoneDialogSuggest) }


    private lateinit var file: File
    init {
        setContentView(R.layout.activity_suggestion_dialog)
    }

    var doneInterceptor: ((title: String, desc: String, phone: String, isVoice: Boolean) -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT > 30)
            window?.setDecorFitsSystemWindows(false)
        else
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setCancelable(true)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        dismissDialog()
    }


    fun setup(
        pageTitle: CharSequence,
        btnTxt: CharSequence,
        inputTypeInt: Int,
        hintTitle: CharSequence? = null,
        hintDesc: CharSequence? = null,
        hintphone: CharSequence? = null,
        maxLenthPhone: Int
    ) {
        txt_titleDialogSugest.text = pageTitle
        btn_sendDialogSuggest.text = btnTxt

        edt_phoneDialogSuggest.inputType = inputTypeInt


        hintTitle?.let {
            lay_titleDialogSuggest.hint = it
        }
        hintDesc?.let {
            lay_descDialogSuggest.hint = it
        }
        hintphone.let {
            lay_phoneDialogSuggest.hint = it
        }
        maxLenthPhone.let {
            lay_phoneDialogSuggest.counterMaxLength = it
        }

    }


    private fun dismissDialog() {
        imgClose.setOnClickListener {
            dismiss()
        }
    }


}

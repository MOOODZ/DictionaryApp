package org.legobyte.khanedan.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityArticleDialogBinding
import java.io.File

class ArticleDialog(context: Context) : Dialog(context) {
    private lateinit var binding: ActivityArticleDialogBinding
    private val ivMain by lazy { findViewById<ImageView>(R.id.ivDialog) }
    private val tvTitle by lazy { findViewById<TextView>(R.id.tvTitle) }
    private val tvArticle by lazy { findViewById<TextView>(R.id.tvArticle) }


    private lateinit var file: File

    init {
        setContentView(R.layout.activity_article_dialog)
    }

    var doneInterceptor: ((title: String, desc: String) -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticleDialogBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT > 30)
            window?.setDecorFitsSystemWindows(false)
        else
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setCancelable(true)
        setCanceledOnTouchOutside(true)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        dismissDialog()
        tvArticle.setOnClickListener {

            doneInterceptor!!.invoke("sdsadsa" , "sdsadsa" )

        }
    }


    fun setup(
        imageView: ImageView,
        title : CharSequence,
        article: CharSequence,
    ) {
        binding.tvTitle.text = title
        binding.tvArticle.text = article
        //binding.ivDialog.setImageURI(s)



       /* hintTitle?.let {
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
        }*/

    }


    private fun dismissDialog() {
        binding.btnCloseDialog.setOnClickListener {
            dismiss()
        }
    }


}

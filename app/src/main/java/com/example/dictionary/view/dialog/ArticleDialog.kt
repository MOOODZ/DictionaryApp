package org.legobyte.khanedan.ui.dialogs

import android.content.Context
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dictionary.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_article_dialog.*
import java.io.File


class ArticleDialog(context: Context) : BottomSheetDialog(context) {
    //private lateinit var binding: ActivityArticleDialogBinding
//    private val ivMain by lazy { findViewById<ImageView>(com.example.dictionary.R.id.ivDialog) }
//    private val tvTitle by lazy { findViewById<TextView>(com.example.dictionary.R.id.tvTitleDialog) }
//    private val tvArticle by lazy { findViewById<TextView>(com.example.dictionary.R.id.tvArticleDialog) }
//    private val btnCancel by lazy { findViewById() }
    private val bottomSheetDialog = layoutInflater.inflate(R.layout.activity_article_dialog, null)


    private lateinit var file: File

    init {
        setContentView(bottomSheetDialog)
    }

    //var doneInterceptor: ((title: String, desc: String) -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dismissDialog()


//        if (Build.VERSION.SDK_INT > 30)
//            window?.setDecorFitsSystemWindows(false)
//        else
//            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

//        requestWindowFeature(STYLE_NO_TITLE)
//        setCancelable(true)
//        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)


        /*tvArticle.setOnClickListener {

            doneInterceptor!!.invoke("sdsadsa" , "sdsadsa" )

        }*/
    }


    fun setup(
        image: String,
        title: String,
        desc: String,
    ) {
        tvTitleDialog.text = title
        tvArticleDialog.text = desc
        Glide
            .with(context)
            .load(image)
            .into(ivDialog)


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
        btn_closeDialog.setOnClickListener {
            dismiss()
        }
    }


}

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dismissDialog()


        window?.setBackgroundDrawableResource(android.R.color.transparent)


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
            .load(image).placeholder(R.drawable.image)
            .into(ivDialog)

    }


    private fun dismissDialog() {
        btn_closeDialog.setOnClickListener {
            dismiss()
        }
    }


}

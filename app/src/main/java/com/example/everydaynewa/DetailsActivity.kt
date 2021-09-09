package com.example.everydaynewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title = intent.getStringExtra("title")
        val imageUrl = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")
        val content = intent.getStringExtra("content")
        setArticleContents(title,imageUrl,description, content)
    }


    private fun setArticleContents(title: String?, imageUrl: String?, descriptionString:String?,contentString: String?) {
        article_title.text = title ?: ""
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(image)
        val descriptionFormatted: String = if (descriptionString != null) {
            Html.fromHtml(descriptionString,
                Html.FROM_HTML_MODE_LEGACY, null, { opening, tag, output, _ ->
                    if (tag.equals("ul") && !opening) output.append("")
                    if (tag.equals("li") && opening) output.append("")
                    if (tag.equals("p") && opening) output.append("")
                }).toString()
        } else {
            ""
        }

        content.text = descriptionFormatted
    }
}
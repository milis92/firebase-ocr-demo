package com.forestgump.boilerplate.home.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.forestgump.boilerplate.GlideApp
import com.forestgump.boilerplate.core.common.mvp.MvpActivity
import com.forestgump.boilerplate.core.domain.models.Document
import com.forestgump.boilerplate.home.R
import javax.inject.Inject

class DetailsActivity : MvpActivity(), DetailsView {

    companion object {
        const val FILE_EXTRA = "file"
    }

    @Inject
    override lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val file = intent.getStringExtra(FILE_EXTRA)
        if (file == null) finish()

        inject()
        presenter.onCreate(this)

        presenter.onCreated(file)

        initToolbar()
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(com.forestgump.boilerplate.R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onFileLoaded(extractedText: Document) {
        findViewById<TextView>(R.id.tv_detected_text).text = extractedText.extractedText
        GlideApp.with(this)
                .load(extractedText.uri)
                .into(findViewById(R.id.original))
    }
}

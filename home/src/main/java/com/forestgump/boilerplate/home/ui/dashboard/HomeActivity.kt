package com.forestgump.boilerplate.home.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.forestgump.boilerplate.core.common.mvp.MvpActivity
import com.forestgump.boilerplate.core.domain.models.Document
import com.forestgump.boilerplate.ext.launchActivity
import com.forestgump.boilerplate.home.R
import com.forestgump.boilerplate.home.ui.details.DetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : MvpActivity(), HomeView {

    companion object {
        private const val GALLERY_REQUEST_CODE = 100
    }

    @Inject
    lateinit var filesAdapter: HomeAdapter

    @Inject
    override lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        inject()
        presenter.onCreate(this)

        setToolbarWithDrawer()

        findViewById<FloatingActionButton>(R.id.btn_add).setOnClickListener {
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    .apply {
                        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
                        type = "image/*"
                    }

            Intent.createChooser(getIntent, getString(R.string.file_picker_select_with)).apply {
                putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
            }.run {
                launchActivity(this, GALLERY_REQUEST_CODE)
            }
        }

        with(findViewById<RecyclerView>(R.id.recycler_view)) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = filesAdapter
        }

        filesAdapter.onItemClicked = {
            launchActivity<DetailsActivity> {
                putExtra(DetailsActivity.FILE_EXTRA, it.id)
            }
        }

        presenter.onCreated()
    }

    private fun setToolbarWithDrawer() {
        val toolbar = findViewById<Toolbar>(com.forestgump.boilerplate.R.id.toolbar)

        setSupportActionBar(toolbar)

        val drawerToggle = object : ActionBarDrawerToggle(
                this,
                findViewById(R.id.root),
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width * slideOffset
                content.translationX = slideX
            }
        }

        findViewById<DrawerLayout>(R.id.root).addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, returnIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, returnIntent)
        if (resultCode != Activity.RESULT_OK) return
        when (requestCode) {
            GALLERY_REQUEST_CODE -> {
                returnIntent?.data?.let {
                    presenter.onImageLoaded(it)
                }
            }
        }
    }

    override fun onDocumentsLoaded(documents: List<Document>) {
        filesAdapter.submitList(documents)
    }

    override fun onDocumentProcessingError(throwable: Throwable?) {
        Snackbar.make(
                findViewById(R.id.content),
                throwable?.message ?: "Unknown error occurred",
                Snackbar.LENGTH_LONG
        )
    }
}

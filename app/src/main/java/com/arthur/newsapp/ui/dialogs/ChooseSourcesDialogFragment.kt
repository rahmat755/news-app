package com.arthur.newsapp.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.arthur.newsapp.NewsApp
import com.arthur.newsapp.R
import com.arthur.newsapp.data.datasource.local.NewsDao
import com.arthur.newsapp.data.utils.COUNTRY_CODES
import com.arthur.newsapp.util.readString
import com.arthur.newsapp.util.saveStringToSP
import kotlinx.android.synthetic.main.choose_country_dialog.*
import kotlinx.coroutines.*
import javax.inject.Inject

class ChooseSourcesDialogFragment : DialogFragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var dao: NewsDao
    private var myJob: Job? = null
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        saveStringToSP(getString(R.string.country_code), COUNTRY_CODES.values.elementAt(pos))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val width = (resources.displayMetrics.widthPixels * 0.7).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.3).toInt()
//        NewsApp.daggerAppComponent.inject(this)
        dialog?.window?.setLayout(width, height)
        ArrayAdapter<String>(
            context!!,
            android.R.layout.simple_spinner_item,
            COUNTRY_CODES.keys.toTypedArray()
        )
            .also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_countries.apply {
                    adapter = it
                    onItemSelectedListener = this@ChooseSourcesDialogFragment
                    val selected = COUNTRY_CODES.values.indexOf(
                        readString(
                            getString(R.string.country_code),
                            "ru"
                        )
                    )
                    spinner_countries.setSelection(selected)
                }
            }
        btn_save.setOnClickListener {
            myJob = CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) { dao.deleteAll() }
                this@ChooseSourcesDialogFragment.dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_country_dialog, container, false)
    }

    override fun onDestroy() {
        myJob?.cancel()
        super.onDestroy()

    }
}
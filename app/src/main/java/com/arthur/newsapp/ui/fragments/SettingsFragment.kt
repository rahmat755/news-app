package com.arthur.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arthur.newsapp.R
import com.arthur.newsapp.ui.dialogs.ChooseCountryDialogFragment
import com.arthur.newsapp.ui.fragments.main_content_screen.MainContentFragment
import kotlinx.android.synthetic.main.settings_fragment.*
import android.content.Context
import com.arthur.newsapp.ui.dialogs.ChooseCategoryDialogFragment
import com.arthur.newsapp.util.saveStringToSP
import timber.log.Timber
import java.io.File


class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tb_settings.run {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                val mainFr = MainContentFragment()
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this@SettingsFragment)
                    ?.replace(R.id.container, mainFr, mainFr.javaClass.simpleName)
                    ?.commit()
            }
        }
        btn_choose_country.setOnClickListener {
            val chooseCountryDialog = ChooseCountryDialogFragment()
            chooseCountryDialog.show(fragmentManager!!,  chooseCountryDialog.javaClass.simpleName)
        }
        btn_choose_category.setOnClickListener {
            val chooseCategoryDialog = ChooseCategoryDialogFragment()
            chooseCategoryDialog.show(fragmentManager!!,  chooseCategoryDialog.javaClass.simpleName)
        }
        btn_clear_cache.setOnClickListener {
            saveStringToSP(getString(R.string.country_code), "ru")
            saveStringToSP(getString(R.string.category), "general")
            if (context != null)
                deleteCache(context!!)
            else return@setOnClickListener
        }
    }

    private fun deleteCache(context: Context) {
        try {
            val dir = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            return dir.delete()
        } else return if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }
}

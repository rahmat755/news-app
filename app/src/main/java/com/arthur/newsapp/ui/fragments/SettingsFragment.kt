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
                    ?.detach(this@SettingsFragment)
//                        ?.hide(this@MainContentFragment)
                    ?.replace(R.id.container, mainFr, mainFr.javaClass.simpleName)
                    ?.commit()
            }
        }
        btn_choose_country.setOnClickListener {
            val d = ChooseCountryDialogFragment()
            d.show(fragmentManager!!, "111")
        }
    }
}

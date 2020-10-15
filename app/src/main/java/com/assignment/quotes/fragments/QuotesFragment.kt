package com.assignment.quotes.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.assignment.quotes.R
import com.assignment.quotes.support.QPagerAdapter
import com.assignment.quotes.viewmodel.QuotesViewModel

class QuotesFragment : Fragment() {

    val quoteViewModel by activityViewModels<QuotesViewModel>()
    lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_quotes, container, false)

        viewPager = view.findViewById(R.id.viewPager) as ViewPager

        quoteViewModel.quotesObservable().observe(
            viewLifecycleOwner,
            {
                val adapter = QPagerAdapter(requireContext(), it)
                viewPager.adapter = adapter
                autoSwitcher()
            }
        )

        return view
    }

    override fun onResume() {
        super.onResume()

        if(!quoteViewModel.quotesDataAlreadExist())
            quoteViewModel.fetchQuotes()
    }

    fun autoSwitcher(){

        Handler().postDelayed({
            viewPager.currentItem += 1
            autoSwitcher()
        }, 5000)

    }
}
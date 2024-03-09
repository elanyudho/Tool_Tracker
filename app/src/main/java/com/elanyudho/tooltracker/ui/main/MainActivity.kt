package com.elanyudho.tooltracker.ui.main

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.tooltracker.R
import com.elanyudho.tooltracker.databinding.ActivityMainBinding
import com.elanyudho.tooltracker.ui.main.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = { ActivityMainBinding.inflate(layoutInflater) }

    override fun setupView() {
        setHeader()
        setTab()
        setTabLayout()
    }

    private fun setTab() {
        val adapter = PagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.my_tools)
                1 -> tab.text = getString(R.string.my_friends)
            }
        }.attach()
    }

    private fun setHeader() {
        binding.headerApp.tvHeader.text = getString(R.string.app_name)
    }

    private fun setTabLayout() {
        with(binding) {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    // Handle logic for reselected tab
                }
            })
        }
    }

}
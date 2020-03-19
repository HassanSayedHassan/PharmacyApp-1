package com.yarenyarsilikal.pharmacy

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter
    (
    private val pageList: ArrayList<Fragment>,
    mainActivity: MainActivity
) : FragmentStateAdapter(mainActivity) {
    override fun getItemCount(): Int = pageList.size

    override fun createFragment(position: Int): Fragment = pageList[position]
}

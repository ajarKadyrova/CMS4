package com.example.customermanagementsystem.pagerAdapter

import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.fragments.*

class GroupsDataPagerAdapter (fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> { GroupDataFragment() }
            else -> { GroupStudentsFragment() }
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        super.getPageTitle(position)
        return when(position) {
            0 -> { "Данные" }
            else -> { "Список учеников" }
        }
    }
    override fun getCount(): Int {
        return 2
    }
}
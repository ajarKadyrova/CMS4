package com.example.customermanagementsystem.pagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.customermanagementsystem.fragments.*

class ClientsPagerAdapter (fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {FirstStage() }
            1 -> { SecondStage() }
            2-> { ThirdStage() }
            3 -> { FourthStage() }
            else -> { FifthStage() }
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        super.getPageTitle(position)
        return when(position) {
            0 -> { "Первый контакт" }
            1 -> { "Звонок" }
            2-> { "Пробный урок" }
            3 -> { "Посетил пробный урок" }
            else -> { "Записался на курсы" }
        }
    }
    override fun getCount(): Int {
        return 4
    }
}
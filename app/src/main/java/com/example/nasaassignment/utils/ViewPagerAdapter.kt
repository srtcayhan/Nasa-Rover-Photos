package com.example.nasaassignment.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nasaassignment.ui.onboarding.FirstOnBoardingFragment
import com.example.nasaassignment.ui.onboarding.SecondOnBoardingFragment
import com.example.nasaassignment.ui.onboarding.ThirdOnBoardingFragment

private const val FRAGMENT_COUNT = 3

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstOnBoardingFragment()
            1 -> SecondOnBoardingFragment()
            2 -> ThirdOnBoardingFragment()
            else -> ThirdOnBoardingFragment()
        }
    }
}
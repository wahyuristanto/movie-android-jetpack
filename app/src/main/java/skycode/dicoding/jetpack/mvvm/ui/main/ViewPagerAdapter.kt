@file:Suppress("DEPRECATION")

package skycode.dicoding.jetpack.mvvm.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by wahyu on 27/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class ViewPagerAdapter(
    private val listStr: ArrayList<String>,
    private val listFrag: ArrayList<Fragment>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = listFrag[position]

    override fun getCount() = listFrag.size

    override fun getPageTitle(position: Int) = listStr[position]
}
package com.example.tv3.activities

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.leanback.widget.BrowseFrameLayout
import com.example.tv3.R
import com.example.tv3.fragment.HomeFragment
import com.example.tv3.utils.Common
import com.example.tv3.utils.Constants
import com.example.tv3.fragment.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    lateinit var navBar: BrowseFrameLayout
    lateinit var fragmentContainer: FrameLayout

    lateinit var btnSearch: TextView
    lateinit var btnHome: TextView


    var SIDE_MENU = false
    var selectedMenu = Constants.MENU_HOME
    lateinit var lastSelectedMenu: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.container)
        changeFragment(HomeFragment())
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
//        closeMenu()
    }

    fun openMenu() {
        val animSlide : Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        navBar.startAnimation(animSlide)

        navBar.requestLayout()
        navBar.layoutParams.width = Common.getWidthInPercent(this, 16)
    }

    fun closeMenu() {
        navBar.requestLayout()
        navBar.layoutParams.width = Common.getWidthInPercent(this, 5)

        fragmentContainer.requestFocus()
        SIDE_MENU = false
    }

//    override fun onKey(view: View?, i: Int, key_event: KeyEvent?): Boolean {
//        when (i) {
//            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
//
//                lastSelectedMenu.isActivated = false
//                view?.isActivated = true
//                lastSelectedMenu = view!!
//
//                when (view.id) {
//                    R.id.btn_search -> {
//                        selectedMenu = Constants.MENU_SEARCH
//                        changeFragment(SearchFragment())
//                    }
//                    R.id.btn_home -> {
//                        selectedMenu = Constants.MENU_HOME
//                        changeFragment(HomeFragment())
//                    }
//                }
//
//            }
//
//            KeyEvent.KEYCODE_DPAD_LEFT -> {
//                if (!SIDE_MENU) {
//                    switchToLastSelectedMenu()
//                    openMenu()
//                    SIDE_MENU = true
//                }
//            }
//        }
//        return false
//    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && SIDE_MENU) {
//            SIDE_MENU = false
//            closeMenu()
//        }
//
//        return super.onKeyDown(keyCode, event)
//    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

//    private fun switchToLastSelectedMenu() {
//        when (selectedMenu) {
//            Constants.MENU_SEARCH -> {
//                btnSearch.requestFocus()
//            }
//            Constants.MENU_HOME -> {
//                btnHome.requestFocus()
//            }
//        }
//    }
}
package com.rappi.movies.introPager

import android.os.Bundle
import com.rappi.movies.R
import com.rappi.movies.utils.ActivityUtils

class IntroPagerActivity : DrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Welcome"
        }

        var fragment: IntroPagerFragment? = supportFragmentManager
                .findFragmentById(R.id.contentIntroPager) as IntroPagerFragment?

        if (fragment == null) {
            fragment = IntroPagerFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFragment)
        }

    }

}

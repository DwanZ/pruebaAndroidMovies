package com.rappi.movies.introPager


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.text.Html
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rappi.movies.R


@Suppress("DEPRECATION")
class IntroPagerFragment : Fragment() {

    lateinit var sildeViewPager: ViewPager
    lateinit var dotLinear: LinearLayout
    lateinit var sliderAdapter: SliderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_intro_pager, container, false)

        sildeViewPager = root.findViewById(R.id.slideViewPager)
        dotLinear = root.findViewById(R.id.dotsLayout)

        val slide_images = arrayOf(R.drawable.logo, R.drawable.sheldon, R.drawable.para_funcionarios)
        val slide_headers = arrayOf("TMDB", "Nerd Stats", "Community")
        val slide_text = arrayOf("The Movie Database (TMDb) is a community built movie and TV database. Every piece of data has been added by our amazing community dating back to 2008.",
                "We all love stats. Here's a few that we find interesting. 411.214 movies, 75.344 TV show, 1'196.965 users, 2'003.266 images and 133.555 Edits Last Week.",
                "Between our staff and community moderators, we're always here to help. Our community grows every day, what do you wait to be part of it?")

        sliderAdapter = SliderAdapter(activity!!.applicationContext, slide_images, slide_headers, slide_text)
        sildeViewPager.adapter = sliderAdapter
        addDotsIndicator(0)
        sildeViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                addDotsIndicator(position)
            }
        })

        return root
    }

    fun addDotsIndicator(position: Int) {
        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)
        val height = dm.heightPixels
        dotLinear.removeAllViews()
        val tv1 = TextView(activity!!.applicationContext)
        tv1.text = Html.fromHtml("&#9679;")
        tv1.textSize = if (height > 800) {
            40F
        } else {
            20F
        }
        if (position == 0) {
            tv1.setTextColor(resources.getColor(R.color.colorPrimary))
        } else {
            tv1.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }
        val tv2 = TextView(activity!!.applicationContext)
        tv2.text = Html.fromHtml("&#9679;")
        tv2.textSize = if (height > 800) {
            40F
        } else {
            20F
        }
        tv2.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        val tv3 = TextView(activity!!.applicationContext)
        tv3.text = Html.fromHtml("&#9679;")
        tv3.textSize = if (height > 800) {
            40F
        } else {
            20F
        }
        tv3.setTextColor(resources.getColor(R.color.colorPrimaryDark))
        dotLinear.addView(tv1)
        dotLinear.addView(tv2)
        dotLinear.addView(tv3)
        val aux = arrayOf(tv1, tv2, tv3)

        if (position > 0) {
            aux[position].setTextColor(resources.getColor(R.color.colorPrimary))
        }
    }

    companion object {
        fun newInstance(): IntroPagerFragment {
            return IntroPagerFragment()
        }
    }

}

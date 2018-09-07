package com.rappi.movies.introPager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rappi.movies.R

class SliderAdapter (val mContext:Context, val slide_images:Array<Int>,
                     val slide_headers:Array<String>, val slide_text:Array<String>):PagerAdapter(){

    lateinit var layoutInflater: LayoutInflater


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun getCount(): Int {
        return slide_headers.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view:View = layoutInflater.inflate(R.layout.slide_layout,container,false)

        val imageView = view.findViewById(R.id.slide_image) as ImageView
        val headerView = view.findViewById(R.id.slide_header) as TextView
        val textView = view.findViewById(R.id.slide_text) as TextView

        imageView.setImageResource(slide_images[position])
        headerView.text = slide_headers[position]
        textView.text = slide_text[position]

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
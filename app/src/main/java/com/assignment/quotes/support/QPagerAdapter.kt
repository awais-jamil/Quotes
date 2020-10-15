package com.assignment.quotes.support

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.PagerAdapter
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.assignment.quotes.R
import com.assignment.quotes.models.Quote
import java.util.*
import kotlin.collections.ArrayList


class QPagerAdapter(var context: Context, var quotes: List<Quote>) : PagerAdapter() {

    var layoutInflater: LayoutInflater
    var fontsList = ArrayList<Int>()
    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        fontsList.add(R.font.roboto)
        fontsList.add(R.font.roboto_medium)
        fontsList.add(R.font.roboto_mediumitalic)
        fontsList.add(R.font.roboto_italic)
        fontsList.add(R.font.roboto_bold)
        fontsList.add(R.font.roboto_bolditalic)
        fontsList.add(R.font.roboto_light)
        fontsList.add(R.font.roboto_lightitalic)
    }

    override fun getCount(): Int {
        return quotes.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = layoutInflater.inflate(R.layout.slide_item, container, false)

        val parentLayout: ConstraintLayout = itemView.findViewById(R.id.parentLayout)
        val quoteText: TextView = itemView.findViewById(R.id.quoteTextV)
        val authorName: TextView = itemView.findViewById(R.id.authorNameTextV)
        container.addView(itemView)

        var generator = ColorGenerator.MATERIAL
        // generate random color
        var color = generator.getRandomColor()

        parentLayout.setBackgroundColor(color)

        val idfx: Int = Random().nextInt(fontsList.size)
        val randomfnts: Int = fontsList.get(idfx)

        val typeface = ResourcesCompat.getFont(context, randomfnts)
        quoteText.setTypeface(typeface)
        authorName.setTypeface(typeface)

        quoteText.text = quotes.get(position).quoteText
        authorName.text = quotes.get(position).quoteAuthor

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}
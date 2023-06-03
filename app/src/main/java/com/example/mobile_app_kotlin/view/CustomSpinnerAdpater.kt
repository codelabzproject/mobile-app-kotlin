package com.example.mobile_app_kotlin.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.service.model.response.SpinnerItem
import com.squareup.picasso.Picasso

class CustomSpinnerAdapter(context: Context, items: List<SpinnerItem>) :
    ArrayAdapter<SpinnerItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item, parent, false)
        val item = getItem(position)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val iconImageView = view.findViewById<ImageView>(R.id.iconImageView)

        titleTextView.text = item?.title
        titleTextView.setTextColor(ContextCompat.getColor(context, R.color.spinner_item_text_color))

        if (item?.iconPng != null) {
            Picasso.get().load(item.iconPng).into(iconImageView)
        } else if (item?.iconDrawable != null) {
            Picasso.get().load(item.iconDrawable).into(iconImageView)
        } else {
            iconImageView.setImageDrawable(null)
        }

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_dropdown, parent, false)
        val item = getItem(position)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val iconImageView = view.findViewById<ImageView>(R.id.iconImageView)

        titleTextView.text = item?.title
        titleTextView.setTextColor(ContextCompat.getColor(context, R.color.spinner_item_text_color))

        if (item?.iconPng != null) {
            Picasso.get().load(item.iconPng).into(iconImageView)
        } else if (item?.iconDrawable != null) {
            Picasso.get().load(item.iconDrawable).into(iconImageView)
        } else {
            iconImageView.setImageDrawable(null)
        }

        return view
    }
}

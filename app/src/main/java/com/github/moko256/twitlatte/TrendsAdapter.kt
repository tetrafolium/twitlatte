/*
 * Copyright 2015-2019 The twitlatte authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.moko256.twitlatte

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.moko256.latte.client.base.entity.Trend

/**
 * Created by moko256 on 2017/07/05.
 *
 * @author moko256
 */

class TrendsAdapter(private val context: Context, private val data: List<Trend>) : RecyclerView.Adapter<TrendsAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return data[position].hashCode().toLong()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TrendsAdapter.ViewHolder {
        return TrendsAdapter.ViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.layout_trend, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: TrendsAdapter.ViewHolder, i: Int) {
        val (name, volume) = data[i]

        viewHolder.text.text = name
        if (volume != -1) {
            viewHolder.volume.visibility = View.VISIBLE
            viewHolder.volume.text = context.getString(R.string.tweet_per_last_24_hours, volume)
        } else {
            viewHolder.volume.visibility = View.GONE
        }
        viewHolder.itemView.setOnClickListener {
            context.startActivity(SearchResultActivity.getIntent(context, name))
        }

    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.trend_text)
        val volume: TextView = itemView.findViewById(R.id.trend_text_volume)
    }
}
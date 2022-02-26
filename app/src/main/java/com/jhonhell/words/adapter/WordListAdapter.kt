package com.jhonhell.words.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonhell.words.R
import com.jhonhell.words.databinding.ItemListWordBinding
import com.jhonhell.words.entities.Word

class WordListAdapter(private val context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordHolder>() {

    private val words = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder.create(context, parent)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.setContent(words[position])
    }

    override fun getItemCount(): Int = words.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(words: List<Word>) {
        this.words.clear()
        this.words.addAll(words)
        notifyDataSetChanged()
    }

    class WordHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun setContent(word: Word) {
            ItemListWordBinding.bind(itemView).apply {
                tvContent.text = word.word
            }
        }

        companion object {
            fun create(context: Context, viewGroup: ViewGroup): WordHolder {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_list_word, viewGroup, false)
                return WordHolder(view)
            }
        }
    }

}
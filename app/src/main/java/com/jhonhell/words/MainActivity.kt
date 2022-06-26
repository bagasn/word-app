package com.jhonhell.words

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import com.jhonhell.words.adapter.WordListAdapter
import com.jhonhell.words.databinding.ActivityMainBinding
import com.jhonhell.words.entities.Word
import com.jhonhell.words.viewmodels.WordViewModel
import com.jhonhell.words.viewmodels.WordViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    } 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val adapter = WordListAdapter(this)
        _binding.recyclerView.adapter = adapter

        wordViewModel.allWords.observe(this) { words ->
            words?.let { adapter.submitList(it) }
        }

        _binding.fabAdd.setOnClickListener {
            showAddWordDialog()
        }
    }

    private fun showAddWordDialog() {
        val dialog = AlertDialog.Builder(this)

        val container = LinearLayout(applicationContext)
        val inputText = EditText(this)
        inputText.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        inputText.maxLines = 1
        inputText.hint = "Type your word"

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(64, 16, 64, 0)
        inputText.layoutParams = layoutParams
        container.addView(inputText)

        dialog.setView(container)
        dialog.setNegativeButton("Cancel", null)
        dialog.setPositiveButton("Submit") { _, _ ->
            val text = inputText.text.toString().trim()
            if (text.isEmpty()) {
                showToast("Invalid word")
                return@setPositiveButton
            }

            wordViewModel.insert(Word(word = text))
        }
        dialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }


}
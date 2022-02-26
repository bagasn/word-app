package com.jhonhell.words.database.repository

import com.jhonhell.words.dao.WordDao
import com.jhonhell.words.entities.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao){

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}
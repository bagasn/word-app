package com.jhonhell.words.dao

import androidx.room.Dao
import androidx.room.Query
import com.jhonhell.words.entities.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table")
    fun getAll(): List<Word>

}

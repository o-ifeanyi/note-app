package com.example.noteapp.data

import androidx.room.*
import com.example.noteapp.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query(value = "SELECT * from notes_table")
    fun getNotes() : Flow<List<Note>>

    @Query(value = "DELETE from notes_table")
    suspend fun deleteAll()

    @Query(value = "SELECT * from notes_table where id=:id")
    suspend fun getNoteById(id: String) : Note

    @Insert(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Delete(entity = Note::class)
    suspend fun delete(note: Note)
}

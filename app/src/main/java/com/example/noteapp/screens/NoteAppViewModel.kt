package com.example.noteapp.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.models.Note
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteAppViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    private val _allNotes = MutableStateFlow<List<Note>>(emptyList())
    val allNotes = _allNotes.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes().distinctUntilChanged().collect {
                if (it.isEmpty()) {
                    Log.d("TAG", "Empty List: ")
                } else {
                    _allNotes.value = it
                }
            }
        }
    }

     fun addNote(note: Note) {
        viewModelScope.launch { noteRepository.addNote(note) }
    }

     fun removeNote(note: Note) {
        viewModelScope.launch { noteRepository.deleteNote(note) }
    }

}
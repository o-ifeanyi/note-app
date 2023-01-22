package com.example.noteapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.components.NoteInputField
import com.example.noteapp.components.NoteList
import com.example.noteapp.models.Note

@Preview
@Composable
fun NotesApp() {
    val titleInput = remember {
        mutableStateOf("")
    }
    val descriptionInput = remember {
        mutableStateOf("")
    }
    val viewModel = viewModel<NoteAppViewModel>()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Notes",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            NoteInputField(
                text = titleInput.value,
                label = "Title"
            ) {
                if (it.all { c -> c.isLetter() || c.isWhitespace() })
                    titleInput.value = it
            }
            NoteInputField(
                text = descriptionInput.value,
                label = "Add a note",
            ) {
                if (it.all { c -> c.isLetter() || c.isWhitespace() })
                    descriptionInput.value = it
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                if (titleInput.value.trim().isNotEmpty() && descriptionInput.value.trim()
                        .isNotEmpty()
                ) {
                    viewModel.addNote(
                        Note(
                            title = titleInput.value,
                            description = descriptionInput.value,
                        )
                    )
                    titleInput.value = ""
                    descriptionInput.value = ""
                }

            }, shape = CircleShape) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.height(12.dp))
            NoteList()
        }

    }
}

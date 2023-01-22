package com.example.noteapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.screens.NoteAppViewModel
import com.example.noteapp.util.formatDate

@Composable
fun NoteList() {
    val viewModel = viewModel<NoteAppViewModel>()
    val notes = viewModel.allNotes.collectAsState().value

    LazyColumn {
        items(notes) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .clip(
                        shape = CircleShape.copy(
                            topStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp),
                            topEnd = CornerSize(15.dp),
                            bottomStart = CornerSize(15.dp)
                        )
                    )
                    .clickable { viewModel.removeNote(it) },
                color = MaterialTheme.colors.primary.copy(.1f)
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = it.title, fontWeight = FontWeight.Bold)
                    Text(text = it.description)
                    Text(
                        text = formatDate(it.createdAt),
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}
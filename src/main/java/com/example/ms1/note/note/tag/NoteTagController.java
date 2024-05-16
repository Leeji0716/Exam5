package com.example.ms1.note.note.tag;

import com.example.ms1.note.ParamHandler;
import com.example.ms1.note.note.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes/{noteId}/tags")
public class NoteTagController {
    private final NoteTagService noteTagService;

    @GetMapping("/create")
    public String create(@PathVariable("noteId") Long noteId, ParamHandler paramHandler, String name){
        NoteTag noteTag = noteTagService.create(noteId, name);
        Long notebookId = noteTag.getNote().getNotebook().getId();
        return paramHandler.getRedirectUrl("/books/%d/notes/%d".formatted(notebookId, noteId));
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("noteId") Long noteId, @PathVariable("id") Long id, Long notebookId, ParamHandler paramHandler){
        noteTagService.delete(id);
        return paramHandler.getRedirectUrl("/books/%d/notes/%d".formatted(notebookId, noteId));
    }
}

package com.example.ms1.note.notebook;

import com.example.ms1.note.MainService;
import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;
    private final MainService mainService;

    @PostMapping("/books/write")
    public String write() {
        Notebook notebook = mainService.saveDefaultNotebook();
        return "redirect:/books/%d".formatted(notebook.getId());

    }

    @PostMapping("/groups/{notebookId}/books/write")
    public String groupWrite(@PathVariable("notebookId") Long notebookId) {
        Notebook notebook = mainService.saveGroupNotebook(notebookId);
        return "redirect:/books/%d".formatted(notebook.getId());
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable("id") Long id) {
        Notebook notebook = notebookService.getNotebook(id);
        Note note = notebook.getNoteList().get(0);

        return "redirect:/books/%d/notes/%d".formatted(id, note.getId());
    }

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        Notebook notebook = notebookService.getNotebook(id);
        notebookService.delete(notebook);

        return "redirect:/";
    }

    @PostMapping("/books/{id}/update")
    public String update(@PathVariable("id") Long id, String name) {
        Notebook notebook = notebookService.getNotebook(id);
        notebookService.update(notebook, name);

        return "redirect:/books/%d".formatted(id);
    }

    @PostMapping("/books/{id}/move")
    public String move(@PathVariable("id") Long id, Long moveTarget) {
        Notebook notebook = notebookService.getNotebook(id);
        notebookService.move(id, moveTarget);

        return "redirect:/books/%d".formatted(id);
    }
}

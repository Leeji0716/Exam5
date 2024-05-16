package com.example.ms1.note.notebook;

import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;

    public Notebook getNotebook(Long notebookId) {
        return notebookRepository.findById(notebookId).orElseThrow();
    }

    public List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }

    public Notebook save(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public void delete(Notebook notebook) {
        notebookRepository.delete(notebook);
    }

    public void update(Notebook notebook, String name) {
        notebook.setName(name);
        notebookRepository.save(notebook);
    }

    public List<Notebook> getTopNotebookList() {
        List<Notebook> notebookList = notebookRepository.findAll();
        List<Notebook> topNotebookList = new ArrayList<>();

        for(Notebook notebook : notebookList){
            if (notebook.getParent() == null){
                topNotebookList.add(notebook);
            }
        }
        return topNotebookList;
    }

    public void move(Long id, Long moveTarget) {
        Notebook notebook = getNotebook(id);
        Notebook targetNotebook = getNotebook(moveTarget);

        notebook.setParent(targetNotebook);

        notebookRepository.save(notebook);
    }
}

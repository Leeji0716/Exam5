package com.example.ms1.note.note.tag.tag;

import com.example.ms1.note.note.tag.NoteTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<NoteTag> noteTagList;
}

package goit.edu.service;

import goit.edu.model.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    Note getById(long id, UUID userId);

    List<Note> listAllByUserId(UUID userId);

    Note add(Note note, UUID userId);

    void deleteById(long id, UUID userId);

    void update(long id, UUID userId, Note note);
}
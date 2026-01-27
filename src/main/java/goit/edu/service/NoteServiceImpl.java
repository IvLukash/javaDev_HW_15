package goit.edu.service;

import goit.edu.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteServiceImpl implements NoteService {
    private final Map<Long, Note> storage;
    private final AtomicLong counter;

    public NoteServiceImpl() {
        this.storage = new ConcurrentHashMap<>();
        this.counter = new AtomicLong(0);
    }

    @Override
    public Note getById(long id, UUID userId) {
        Note note = storage.get(id);
        if (note == null) {
            throw new NoSuchElementException();
        }
        if (!note.getUserId().equals(userId)) {
            throw new SecurityException();
        }
        return note;
    }

    @Override
    public List<Note> listAllByUserId(UUID userId) {
        return storage.values().stream()
                .filter(note -> note.getUserId().equals(userId))
                .toList();
    }

    @Override
    public Note add(Note note, UUID userId) {
        long id = counter.incrementAndGet();
        note.setId(id);
        note.setUserId(userId);
        storage.put(id, note);
        return note;
    }

    @Override
    public void deleteById(long id, UUID userId) {
        getById(id, userId);
        storage.remove(id);
    }

    @Override
    public void update(long id, UUID userId, Note note) {
        Note updatedNote = getById(id, userId);
        updatedNote.setTitle(note.getTitle());
        updatedNote.setContent(note.getContent());
    }
}

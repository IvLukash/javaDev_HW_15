package goit.edu.web.controller;

import goit.edu.model.Note;
import goit.edu.service.NoteServiceImpl;
import goit.edu.web.webhelper.UserSession;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteServiceImpl service;
    private final UserSession userSession;

    @GetMapping("/list")
    public String getAllNotes(
            Model model,
            HttpSession session
    ) {
        UUID userId = userSession.getOrCreateUserId(session);
        model.addAttribute("notes", service.listAllByUserId(userId));
        return "all_notes";
    }

    @GetMapping("/get")
    public String getNoteById(
            Model model,
            @RequestParam("id") String idParam,
            HttpSession session
    ) {
        UUID userId = userSession.getOrCreateUserId(session);
        long id = Long.parseLong(idParam);
        model.addAttribute("note", service.getById(id, userId));
        return "get_note";
    }

    @GetMapping("/new")
    public String getCreateForm(Model model) {
        model.addAttribute("note", new Note());
        return "create_form";
    }

    @PostMapping("/new")
    public String createNote(
            @ModelAttribute("note") Note note,
            HttpSession session
    ) {
        UUID userId = userSession.getOrCreateUserId(session);
        service.add(note, userId);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String getEditForm(
            Model model,
            @RequestParam("id") String idParam,
            HttpSession session
    ) {
        UUID userId = userSession.getOrCreateUserId(session);
        long id = Long.parseLong(idParam);
        model.addAttribute("note", service.getById(id, userId));
        return "edit_form";
    }

    @PostMapping("/edit")
    public String updateNote(
            @RequestParam("id") long id,
            HttpSession session,
            @ModelAttribute("note") Note note
    ) {
        UUID userId = userSession.getOrCreateUserId(session);
        service.update(id, userId, note);
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(
            @RequestParam("id") long id,
            HttpSession session
    ) {
        UUID userId = userSession.getOrCreateUserId(session);
        service.deleteById(id, userId);
        return "redirect:/note/list";
    }
}

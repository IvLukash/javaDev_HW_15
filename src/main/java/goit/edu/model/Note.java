package goit.edu.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Note {
    @EqualsAndHashCode.Include
    private long id;
    private String title;
    private String content;
    private UUID userId;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

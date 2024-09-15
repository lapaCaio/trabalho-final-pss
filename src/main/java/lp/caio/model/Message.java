package lp.caio.model;

import java.time.LocalDate;

public class Message {
    private long id;
    private String title;
    private String content;
    private LocalDate creationDate;

    public Message(String title, String content, LocalDate creationDate) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + 
               ", Content: " + content + ", Creation Date: " + creationDate;
    }
}

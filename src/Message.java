package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Message {
    private String text;
    private final String id;
    private final User sender;
    private final List<React> reacts;
    private Date date;

    public Message(String id, String text, User sender) {
        this.text = text;
        this.sender = sender;
        this.reacts = new ArrayList<>();
        this.date = new Date();
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public User getSender() {
        return sender;
    }

    public List<React> getReacts() {
        return reacts;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public React addReact(Reaction reaction, User user) {
        for (React r : reacts) {
            if (r.getUser().equals(user)) {
                r.setReaction(reaction);
                System.out.println("Reaction updated");
                return r;
            }
        }
        String id = "RE" +  UUID.randomUUID().toString().substring(0, 8);
        React react = new React(id, reaction, user);
        System.out.println("Reaction added");
        reacts.add(react);
        return react;
    }

    public void removeReact(User user) {
        for (React r : reacts) {
            if (r.getUser().equals(user)) {
                reacts.remove(r);
                System.out.println("Reaction removed");
                return;
            }
        }
    }

    public String getId() {
        return id;
    }


}

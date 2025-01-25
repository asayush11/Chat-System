package src;

public class React {
    private Reaction reaction;
    private final String id;
    private final User user;

    public React(String id, Reaction reaction, User user) {
        this.reaction = reaction;
        this.user = user;
        this.id = id;
    }
    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }
}

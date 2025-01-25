package src;

public class User {
    private final String id;
    private final String name;
    private final String phoneNumber;
    private final String email;

    public User(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Group addGroup(ChatSystem chatSystem, String name) {
        return chatSystem.addGroup(this, name);
    }

    public void removeGroup(ChatSystem chatSystem, String groupID) {
        chatSystem.removeGroup(groupID, this);
    }

    public User addMemberInGroup(Group group, User user) {
        return group.addMember(user, this, false);
    }

    public void removeMemberFromGroup(Group group, String userID) {
        group.removeMember(userID, this.getId());
    }

    public Message addMessage(Group group, String text) {
        return group.addMessage(text, this);
    }

    public void removeMessage(Group group, String messageID) {
        group.removeMessage(messageID);
    }

    public void editMessage(Group group, String messageID, String text) {
        group.editMessage(messageID, text, this);
    }

    public React addReact(Group group, String messageID, Reaction reaction) {
        return group.reactToMessage(messageID, reaction, this);
    }

    public void removeReact(Group group, String messageID) {
        group.removeReaction(messageID, this);
    }
}

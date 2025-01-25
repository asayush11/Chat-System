package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Group {
    private String name;
    private final String id;
    private final List<User> members;
    private final List<Message> messages;

    public Group(String name, String id) {
        this.name = name;
        this.id = id;
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
        System.out.println("Group " + name + " created");
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    public User addMember(User user, User addedBy, boolean isAdmin) {
        boolean isMember = isAdmin;
        for (User member : members) {
            if (member.getId().equals(addedBy.getId())) {
                isMember = true;
                break;
            }
        }

        if(!isMember) {
            System.out.println("You are not a member of this group");
            return null;
        }

        for(User member : members) {
            if(member.getId().equals(user.getId())) {
                return member;
            }
        }
        members.add(user);
        System.out.println("User " + user.getName() + " added to group " + name);
        return user;
    }

    public void removeMember(String userID, String removerID) {
        boolean isMember = false;
        for (User member : members) {
            if (member.getId().equals(removerID)) {
                isMember = true;
                break;
            }
        }

        if(!isMember) {
            System.out.println("You are not a member of this group");
            return ;
        }

        for (User member : members) {
            if (member.getId().equals(userID)) {
                members.remove(member);
                System.out.println("User " + member.getName() + " removed from group " + name);
                return;
            }
        }
    }

    public Message addMessage(String text, User sender) {
        boolean isMember = false;
        for (User member : members) {
            if (member.getId().equals(sender.getId())) {
                isMember = true;
                break;
            }
        }

        if(!isMember) {
            System.out.println("You are not a member of this group");
            return null;
        }

        String id = "MS" +  UUID.randomUUID().toString().substring(0, 8);
        Message message = new Message(id, text, sender);
        messages.add(message);
        System.out.println("Message added to group " + name + " by " + message.getSender().getName() + " the content is" + message.getText());
        return message;
    }

    public void removeMessage(String messageID) {
        for (Message message : messages) {
            if (message.getId().equals(messageID)) {
                messages.remove(message);
                System.out.println("Message removed");
                return;
            }
        }
        System.out.println("Message not found");
    }

    public void editMessage(String messageID, String text, User editor) {
        for (Message message : messages) {
            if (message.getId().equals(messageID) && message.getSender().getId().equals(editor.getId())) {
                message.setText(text);
                message.setDate(new Date());
                System.out.println("Message edited");
                return;
            }
        }
        System.out.println("Message not found or you are not the sender");
    }

    public React reactToMessage(String messageID, Reaction reaction, User user) {
        for (Message message : messages) {
            if (message.getId().equals(messageID)) {
                React react = message.addReact(reaction, user);
                System.out.println("Reaction "  + react.getReaction() + " added to message by " + react.getUser().getName() );
                return react;
            }
        }
        return null;
    }

    public void removeReaction(String messageID, User user) {
        for (Message message : messages) {
            if (message.getId().equals(messageID)) {
                message.removeReact(user);
                System.out.println("Reaction removed by " + user.getName());
                return;
            }
        }
    }


    private void listMessages() {
        System.out.println("Messages in group " + name);
        for (Message message : messages) {
            System.out.println("Message: " + message.getText() + " by " + message.getSender().getName());
            for (React react : message.getReacts()) {
                System.out.println("Reaction: " + react.getReaction() + " by " + react.getUser().getName());
            }
        }
    }

    public void listMembers() {
        System.out.println("Members in group " + name);
        for (User member : members) {
            System.out.println("Member: " + member.getName());
        }
        listMessages();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }
}

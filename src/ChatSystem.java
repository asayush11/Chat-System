package src;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatSystem {
    private final List<User> users;
    private final List<Group> groups;
    private static ChatSystem instance;

    private ChatSystem() {
        this.users = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public synchronized static ChatSystem getInstance() {
        if (instance == null) {
            instance = new ChatSystem();
        }
        return instance;
    }

    public User addUser(String name, String phoneNumber, String email) {
        for (User u : users) {
            if (u.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("User with this number already exists");
                return u;
            }
        }
        String userID = "US" + UUID.randomUUID().toString().substring(0, 8);
        User user = new User(userID, name, phoneNumber, email);
        users.add(user);
        System.out.println("User " + name + " added in system");
        return user;
    }

    public void removeUser(String userID) {
        for (User user : users) {
            if (user.getId().equals(userID)) {
                removeUserFromAllGroups(userID);
                users.remove(user);
                System.out.println("User " + user.getName() + " removed from system");
                return;
            }
        }
    }

    public Group addGroup(User user, String name) {
        String id = "GR" +  UUID.randomUUID().toString().substring(0, 8);
        Group group = new Group(name, id);
        group.addMember(user, null, true);
        groups.add(group);
        System.out.println("Group " + name + " created by " + user.getName());
        return group;
    }

    public void removeGroup(String groupID, User user) {
        for (Group group : groups) {
            if (group.getId().equals(groupID)) {
                List<User> members = group.getMembers();
                boolean isMember = false;
                for (User member : members) {
                    if (member.getId().equals(user.getId())) {
                        isMember = true;
                        break;
                    }
                }
                if(!isMember) {
                    System.out.println("You are not a member of this group");
                    return;
                }
                groups.remove(group);
                System.out.println("Group " + group.getName() + " removed from system");
                return;
            }
        }
    }

    private void removeUserFromAllGroups(String userID) {
        for (Group group : groups) {
            group.removeMember(userID, userID);
        }
    }

    public void displayUsers(){
        System.out.println("Users in system:");
        for (User user : users) {
            System.out.println("User: " + user.getName() + " ID: " + user.getId());
        }
    }

    public void displayGroups(){
        System.out.println("Groups in system:");
        for (Group group : groups) {
            System.out.println("Group: " + group.getName() + " ID: " + group.getId());
        }
    }

    public void displayGroupInfo(String groupID){
        for (Group group : groups) {
            if (group.getId().equals(groupID)) {
                group.listMembers();
                return;
            }
        }
    }
}

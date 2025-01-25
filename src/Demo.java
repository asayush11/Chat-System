package src;

public class Demo {
    public static void main(String[] args) {
        ChatSystem chatSystem = ChatSystem.getInstance();

        User user1 = chatSystem.addUser("Alice", "123456789", "qwe@123");
        User user2 = chatSystem.addUser("Bob", "123456789", "qwe@123");
        user2 = chatSystem.addUser("Bob", "123456780", "qwe@123");
        User user3 = chatSystem.addUser("Veena", "123456781", "qwe@123");
        User user4 = chatSystem.addUser("Roman", "123466789", "qwe@123");

        Group group1 = user1.addGroup(chatSystem, "Mates");
        Group group2 = user2.addGroup(chatSystem, "Friends");
        user1.addMemberInGroup(group1, user3);
        user1.addMemberInGroup(group2, user3);
        user2.addMemberInGroup(group1, user4);

        Message message1 = user1.addMessage(group1, "Welcome");
        Message message2 = user2.addMessage(group1, "Welcome");
        Message message3 = user2.addMessage(group2, "Welcome");

        React react1 = user3.addReact(group1, message1.getId(), Reaction.LOVE);
        React react2 = user3.addReact(group2, message3.getId(), Reaction.LOVE);
        React react3 = user3.addReact(group1, message1.getId(), Reaction.HAHA);
        React react4 = user1.addReact(group1, message1.getId(), Reaction.HAHA);

        chatSystem.displayUsers();
        chatSystem.displayGroups();
        user2.removeGroup(chatSystem, group2.getId());
        user2.removeGroup(chatSystem, group1.getId());

        user1.editMessage(group1, message1.getId(), "Listen");
        user1.removeMessage(group1, message1.getId());
        chatSystem.removeUser(user3.getId());
    }
}

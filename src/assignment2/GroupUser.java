package assignment2;

import java.util.HashMap;
import java.util.Map;

import assignment2.Visitor;



public class GroupUser extends User {

    private Map<String,User> groupUsers;

    public GroupUser(String id) {
        super(id);
        groupUsers = new HashMap<String,User>();
    }

    public Map<String,User> getGroupUsers() {
        return groupUsers;
    }

// Add User in group
    
    public User addUserInGroup(User user) {
        if (!this.contains(user.getID())) {
            this.groupUsers.put(user.getID(), user);
        }
        return this;
    }

// Composite methods
    
    @Override
    public boolean contains(String id) {
        boolean contains = false;
        for (User user : groupUsers.values()) {
            if (user.contains(id)) {
                contains = true;
            }
        }
        return contains;
    }

// Returns Single user count
    
    @Override
    public int getSingleUserAmmount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            count += user.getSingleUserAmmount();
        }
        return count;
    }

// Returns group user count
    
    @Override
    public int getGroupUserAmmount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            if (user.getClass() == GroupUser.class) {
                ++count;
                count += user.getGroupUserAmmount();
            }
        }
        return count;
    }

// Returns total number of messages
    @Override
    public int getMessageCount() {
        int msgCount = 0;
        for (User user : this.groupUsers.values()) {
            msgCount += user.getMessageCount();
        }
        return msgCount;
    }

// Observer methods
    
    @Override
    public void update(Subject subject) {
        for (User user : groupUsers.values()) {
            ((Observer) user).update(subject);
        }
    }

// Visitor methods
    public void accept(Visitor visitor) {
        for (User user : groupUsers.values()) {
            user.accept(visitor);
        }
        visitor.visitGroupUser(this);
    }

// Private methods
    
    private boolean containsGroupUser() {
        boolean containsGroup = false;
        for (User user : this.groupUsers.values()) {
            if (user.getClass() == GroupUser.class) {
                containsGroup = true;
            }
        }
        return containsGroup;
    }

}
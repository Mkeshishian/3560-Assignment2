package assignment2;

import assignment2.GroupUser;
import assignment2.SingleUser;
import assignment2.User;

// Concrete visitor in getting total number of messages sent

public class MessagesTotalVisitor implements Visitor {

    @Override
    public int visitUser(User user) {
        int count = 0;

        if (user.getClass() == SingleUser.class) {
            count += visitSingleUser(user);
        } else if (user.getClass() == GroupUser.class) {
            count += visitGroupUser(user);
        }

        return count;
    }

    @Override
    public int visitSingleUser(User user) {
        return ((SingleUser) user).getMessageCount();
    }

    @Override
    public int visitGroupUser(User user) {
        int count = 0;

        for (User u : ((GroupUser) user).getGroupUsers().values()) {
            count += visitUser(u);
        }

        return count;
    }

}
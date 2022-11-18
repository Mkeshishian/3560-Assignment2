package assignment2;

import assignment2.GroupUser;
import assignment2.SingleUser;
import assignment2.User;

// This is a concrete visitor as it gets the total number of SingleUser under the User

public class UserTotalVisitor implements Visitor {

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
        return 1;
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
package assignment2;

import assignment2.GroupUser;
import assignment2.SingleUser;
import assignment2.User;


// Concrete visitor for obtaining total number of GroupUser
// under the specified User

public class GroupTotalVisitor implements Visitor {

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
        return 0;
    }

    @Override
    public int visitGroupUser(User user) {
        int count = 0;

        for (User u : ((GroupUser) user).getGroupUsers().values()) {
            if (u.getClass() == GroupUser.class) {
                ++count;
            }
            count += visitUser(u);
        }

        return count;
    }

}
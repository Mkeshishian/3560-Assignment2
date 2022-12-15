package assignment2;

import assignment2.GroupUser;
import assignment2.Observer;
import assignment2.User;

import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Component;
import java.awt.GridBagConstraints;


// Admin control panel that uses the Singleton design pattern

public class AdminControlPanel extends ControlPanel {

    private static AdminControlPanel INSTANCE;
    private static JFrame frame;
    private JPanel treePanel;
    private JPanel addUserPanel;
    private JPanel openUserViewPanel;
    private JPanel showInfoPanel;

    private DefaultMutableTreeNode root;
    private Map<String, Observer> allUsers;

    public static AdminControlPanel getInstance() {
        if (INSTANCE == null) {
            synchronized (Driver.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AdminControlPanel();
                }
            }
        }
        return INSTANCE;
    }

    private AdminControlPanel() {
        super();

        initializeComponents();
        addComponents();
    }

    private void addComponents() {
        addComponent(frame, treePanel, 0, 0, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, addUserPanel, 1, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, openUserViewPanel, 1, 2, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, showInfoPanel, 1, 4, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initializeComponents() {
        frame = new JFrame("Mini-Twitter App");
        formatFrame();

        allUsers = new HashMap<String, Observer>();
        root = new GroupUser("Root");
        allUsers.put(((User) root).getID(), (Observer) this.root);

        treePanel = new TreePanel(root);
        addUserPanel = new AddUserPanel(treePanel, allUsers);
        openUserViewPanel = new OpenUserViewPanel(treePanel, allUsers);
        showInfoPanel = new ShowInfoPanel(treePanel);

// set buttons to respond to ENTER key, remove default response to SPACE key
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        InputMap im = (InputMap) UIManager.get("Button.focusInputMap");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "none");
// adding validation
        Component validateButton = null;
		add(validateButton);

    }
    // Create a method that finds the user who made the last update

    public User findLastUpdatedUser(List<User> users) {
        User lastUpdatedUser = null;
        long lastUpdateTime = 0;
        for (User user : users) {
            if (user.getLastUpdateTime() > lastUpdateTime) {
                lastUpdatedUser = user;
                lastUpdateTime = user.getLastUpdateTime();
            }
        }
		return lastUpdatedUser;
    }
    {
 // Create a new UserView object
    UserViewPanel view = new UserViewPanel(allUsers, null, root);

    User User = null;
	// Show the creation time of the User object in the UserView
    view.showCreationTime(User);
}
    private void formatFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

}
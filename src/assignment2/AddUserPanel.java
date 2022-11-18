package assignment2;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import assignment2.GroupUser;
import assignment2.Observer;
import assignment2.SingleUser;
import assignment2.User;


// Panel containing text fields and buttons to add
// SingleUser or GroupUser.


public class AddUserPanel extends ControlPanel {

    private JPanel treePanel;
    private Map<String, Observer> allUsers;

    private JButton addUserButton;
    private JButton addGroupButton;
    private JTextField userId;
    private JTextField groupId;

    public AddUserPanel(JPanel treePanel, Map<String, Observer> allUsers) {
        super();
        this.treePanel = treePanel;
        this.allUsers = allUsers;

        initializeComponents();
        addComponents();
    }

    private void addComponents() {
        addComponent(this, userId, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, addUserButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, groupId, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, addGroupButton, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initializeComponents() {
        userId = new JTextField("User ID");
        groupId = new JTextField("Group ID");

        addUserButton = new JButton("Add User");
        initializeAddUserButtonActionListener();

        addGroupButton = new JButton("Add Group");
        initializeAddGroupButtonActionListener();
    }

    private void initializeAddUserButtonActionListener() {
        addUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
// check if user ID already exists
                if (allUsers.containsKey(userId.getText())) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error!",
                            "User already exists!\nPlease choose a different user name.",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Observer child = new SingleUser(userId.getText());

                    allUsers.put(((User) child).getID(), child);
                    ((TreePanel) treePanel).addSingleUser((DefaultMutableTreeNode) child);
                }
            }
        });
    }

    private void initializeAddGroupButtonActionListener() {
        addGroupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
// check if user ID already exists
                if (allUsers.containsKey(groupId.getText())) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error!",
                            "User already exists!\nPlease choose a different user name.",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Observer child = new GroupUser(groupId.getText());

                    allUsers.put(((User) child).getID(), child);
                    ((TreePanel) treePanel).addGroupUser((DefaultMutableTreeNode) child);
                }
            }
        });
    }

}

package assignment2;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import assignment2.GroupUser;
import assignment2.Observer;
import assignment2.SingleUser;
import assignment2.User;
import assignment2.ControlPanel;
import assignment2.TreePanel;

// Panel on main UI

public class OpenUserViewPanel extends ControlPanel {

    private JButton openUserViewButton;
    private JPanel spacerPanel;

    private JPanel treePanel;
    private Map<String, Observer> allUsers;
    private Map<String, JPanel> openPanels;

// Create the pannel
    
    public OpenUserViewPanel(JPanel treePanel, Map<String, Observer> allUsers) {
        super();

        this.treePanel = treePanel;
        this.allUsers = allUsers;
        initializeComponents();
        addComponents();
    }

    private void addComponents() {
        addComponent(this, openUserViewButton, 1, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, spacerPanel, 1, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initializeComponents() {
        openPanels = new HashMap<String, JPanel>();

        openUserViewButton = new JButton("Open User View");
        initializeOpenUserViewActionListener();

// Empty spacer
        spacerPanel = new JPanel();
    }


    private DefaultMutableTreeNode getSelectedNode() {
        JTree tree = ((TreePanel) treePanel).getTree();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        if (!((TreePanel) treePanel).getRoot().equals(selectedNode)) {
            selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
        }

        return selectedNode;
    }


    private void initializeOpenUserViewActionListener() {
        openUserViewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
// get User selected in TreePanel
                DefaultMutableTreeNode selectedNode = getSelectedNode();

// open user view UI on click, only open one window per User
                if (!allUsers.containsKey(((User) selectedNode).getID())) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error!",
                            "No such user exists!",
                            JOptionPane.ERROR_MESSAGE);
                } else if (selectedNode.getClass() == GroupUser.class) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error!",
                            "Cannot open user view for a group!",
                            JOptionPane.ERROR_MESSAGE);
                } else if (openPanels.containsKey(((User) selectedNode).getID())) {
                    InfoDialogBox dialogBox = new InfoDialogBox("Error!",
                            "User view already open for " + ((User) selectedNode).getID() + "!",
                            JOptionPane.ERROR_MESSAGE);
                } else if (selectedNode.getClass() == SingleUser.class) {
                    UserViewPanel userView = new UserViewPanel(allUsers, openPanels, selectedNode);
                    openPanels.put(((User) selectedNode).getID(), userView);
                }
            }
        });
    }

}
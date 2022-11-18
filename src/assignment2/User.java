package assignment2;


import assignment2.Visitor;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("unused")
public abstract class User extends DefaultMutableTreeNode implements Observer {


	private static final long serialVersionUID = -9069854940981345363L;
	private String id;
    private int messageCount;

    public abstract boolean contains(String id);
    public abstract int getSingleUserAmmount();
    public abstract int getGroupUserAmmount();

    public User(String id) {
        super(id);
        this.id = id;
        this.setMessageCount(0);
    }

// Returns user ID
    
    public String getID() {
        return id;
    }

// Returns message ammount sent
    
    public int getMessageCount() {
        return messageCount;
    }

// Sets message count
    
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

// Visitor method

    public abstract void accept(Visitor visitor);

}
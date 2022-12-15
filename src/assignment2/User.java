package assignment2;


import assignment2.Visitor;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("unused")
public abstract class User extends DefaultMutableTreeNode implements Observer {

	private long lastUpdateTime;
	private static final long serialVersionUID = -9069854940981345363L;
	private String id;
    private int messageCount;
    // Add a creationTime attribute of type long
    private long creationTime;
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
 // Add a method that updates the lastUpdateTime attribute to the current system timestamp
    public void updateLastUpdateTime() {
        this.lastUpdateTime = System.currentTimeMillis();
    }
 // Modify the postTweet() method to call the updateLastUpdateTime() method
    public void postTweet(String tweet) {
        // Post the tweet...
        
        // Update the lastUpdateTime attribute of the User object and its followers
        updateLastUpdateTime();
    }
    public void showLastUpdateTime(User user) {}
 // Add a getLastUpdateTime() method that returns the value of the lastUpdateTime attribute
    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }
    
// Visitor method

    public abstract void accept(Visitor visitor);
    
// Add a constructor that sets the creationTime attribute to the current system timestamp
    public User() {
        this.creationTime = System.currentTimeMillis();
    }
	protected abstract long getCreationTime();
}
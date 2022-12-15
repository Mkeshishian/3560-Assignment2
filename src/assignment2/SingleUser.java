package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SingleUser extends User implements Subject {

    
	private static final long serialVersionUID = 1L;
// Positive words
	private static final List<String> POSITIVE_WORDS = Arrays.asList("good", "great", "excellent", "amazing", "terrific", "helpful");

    private Map<String, Observer> followers;
    private Map<String, Subject> following;
    private List<String> newsFeed;

    private String RecentMessage;
    private int PositiveCount;

    public SingleUser(String id) {
        super(id);
        followers = new HashMap<String, Observer>();
        followers.put(this.getID(), this);
        following = new HashMap<String, Subject>();
        newsFeed = new ArrayList<String>();
    }


// Returns the SingleUser followers of this User.

    public Map<String, Observer> getFollowers() {
        return followers;
    }

// Returns single users that the said user is following
    
    public Map<String, Subject> getFollowing() {
        return following;
    }

// Returns news feed
    public List<String> getNewsFeed() {
        return newsFeed;
    }

// Sends message to feed and checks if positive message
    
    public void sendMessage(String message) {
        this.RecentMessage = message;
        this.setMessageCount(this.getMessageCount() + 1);

        if (isPositive(message)) {
            ++PositiveCount;
        }

        notifyObservers();
    }

// Most recent message the user has received
    
    public String getRecentMessage() {
        return this.RecentMessage;
    }

// # of positive messages containing the positive keywords
    public int getPositiveCount() {
        return PositiveCount;
    }

// Composite Method
    
    @Override
    public boolean contains(String id) {
        return this.getID().equals(id);
    }

// Total # of group users
    
    @Override
    public int getGroupUserAmmount() {
        return 0;
    }

// Total # of single users
    @Override
    public int getSingleUserAmmount() {
        return 1;
    }

// Observer method
 
// Updates the news feed of this User with the most recent
// message sent by the specified subject User.

    @Override
    public void update(Subject subject) {
        newsFeed.add(0, (((SingleUser) subject).getID() + ": " + ((SingleUser) subject).getRecentMessage()));
    }

// Subject methods

// Adds the specified observer User as a follower of
// this subject User.

    @Override
    public void attach(Observer observer) {
        addFollower(observer);
    }

// Updates observer that are followers of the user
    
    public void notifyObservers() {
        for (Observer obs : followers.values()) {
            obs.update(this);
        }
    }

// Visitor Methods

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSingleUser(this);
    }

// Adds user as follower
    
    private void addFollower(Observer user) {
        this.getFollowers().put(((User) user).getID(), user);
        ((SingleUser) user).addUserToFollow(this);
    }

// Adds user to follow
    
    private void addUserToFollow(Subject toFollow){
        if (toFollow.getClass() == SingleUser.class) {
            getFollowing().put(((User) toFollow).getID(), toFollow);
        }
    }

// Determines if word is positive in given tweet
    
    private boolean isPositive(String message) {
        boolean positive = false;
        message = message.toLowerCase();
        for (String word : POSITIVE_WORDS) {
            if (message.contains(word)) {
                positive = true;
            }
        }
        return positive;
    }


	@Override
	protected long getCreationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}

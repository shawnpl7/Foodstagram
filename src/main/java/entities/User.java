package entities;
import java.util.HashMap;
import java.util.ArrayList;

// import other required classes
// import list of cuisine categories (?)

/**
 * User class stores: username, password, HashMap mapping cuisine to number of likes (Integer) user put for that
 * cuisine (String), a String bio, a list of Users that follow User (followers), a list of Users the user is following,
 * a list of Posts the user has posted
 */
public class User {
    private String username, password, bio;
    // HashMap<String cuisineCategory, int likes>
    private HashMap<String, Integer> likeHistory;
    private ArrayList<User> followers, following;
    private ArrayList<Post> posts;

    /**
     * explanation of User
     */
    // constructors
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.bio = "";
        this.likeHistory = new HashMap<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public User(String username, String password, String bio, HashMap<String, Integer> likeHistory,
                ArrayList<User> followers, ArrayList<User> following, ArrayList<Post> posts){
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.likeHistory = likeHistory;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        // Should like_history, followers, following, and posts be initialized as an empty HashMap / ArrayList?
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLikeHistory(HashMap<String, Integer> like_history) {
        this.likeHistory = likeHistory;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    // getters
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBio() {
        return this.bio;
    }

    public HashMap<String, Integer> getLikeHistory() {
        return this.likeHistory;
    }

    public ArrayList<User> getFollowers() {
        return this.followers;
    }

    public ArrayList<User> getFollowing() {
        return this.following;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}

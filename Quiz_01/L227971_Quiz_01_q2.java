import java.util.ArrayList;

class User {
    private int user_id;
    private String name;
    private String email;
    private String password;
    public ArrayList<Post> posts = null; // 1 user can have multiple posts

    public User(int user_id, String name, String email, String password) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.posts = new ArrayList<Post>();
    }

    public void add_post(int post_id, String post_body) {
        Post p = new Post(post_id, this.user_id, post_body);
        posts.add(p);
    }

    public void print_amount() {
        System.out.println("Posts: " + this.posts.size());
        int comments = 0;
        int p = this.posts.size();
        for (int i = 0; i < p; i++) {
            comments += this.posts.get(i).comments.size();
        }
        System.out.println("Comments: " + comments);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class Post {
    private int post_id;
    private int user_id;
    private String post_body;
    public ArrayList<Comment> comments = null;

    public Post(int post_id, int user_id, String post_body) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.post_body = post_body;
        this.comments = new ArrayList<Comment>();
    }

    public void add_comment(String comment_body) {
        Comment c = new Comment(this.post_id, this.user_id, comment_body);
        this.comments.add(c);
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }
}

class Comment {
    private int post_id;
    private int user_id;
    private String comment_body;

    public Comment(int post_id, int user_id, String comment_body) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.comment_body = comment_body;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComment_body() {
        return comment_body;
    }

    public void setComment_body(String comment_body) {
        this.comment_body = comment_body;
    }
}

public class L227971_Quiz_01_q2 {
    public static void main(String[] args) {
        User user = new User(0, "Hasan", "l227971@lhr.nu.edu.pk", "Secret, not for you");

        Post p1 = new Post(0, 0, "Post 1");
        Comment c1_1 = new Comment(0, 0, "Comment 1");
        Comment c1_2 = new Comment(0, 0, "Comment 2");
        p1.comments.add(c1_1);
        p1.comments.add(c1_2);

        Post p2 = new Post(1, 0, "Post 2");
        Comment c2_1 = new Comment(1, 0, "Comment 1");
        Comment c2_2 = new Comment(1, 0, "Comment 2");
        Comment c2_3 = new Comment(1, 0, "Comment 3");
        p2.comments.add(c2_1);
        p2.comments.add(c2_2);
        p2.comments.add(c2_3);
        user.posts.add(p1);
        user.posts.add(p2);

        user.print_amount();
    }
}

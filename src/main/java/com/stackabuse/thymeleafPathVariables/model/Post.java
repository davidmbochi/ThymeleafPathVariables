package com.stackabuse.thymeleafPathVariables.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(name = "post")
public class Post {

    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(
            name = "story",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String story;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(String story) {
        this.story = story;
    }

    public void addComment(Comment comment){
        if (!this.comments.contains(comment)){
            this.comments.add(comment);
            comment.setPost(this);


        }
    }

    public void removeComment(Comment comment){
        if (this.comments.contains(comment)){
            this.comments.remove(comment);
            comment.setPost(null);

        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", story='" + story + '\'' +
                '}';
    }
}

package com.app.forum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String post;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Post() {
    }

    public Post(String post, User user) {
        this.post = post;
        this.user = user;
    }

}

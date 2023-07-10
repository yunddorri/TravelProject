package com.example.travel.entity;

import com.example.travel.dto.PostsFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@Getter @Setter @ToString
public class Posts extends BaseTimeEntity {
    @Id // pk
    @Column(name="posts_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String title; // 제목
    private String body; // 내용

//    // Post 엔티티 생성 메소드
//    public static Posts createPosts(PostsFormDto postsFormDto) {
//        Posts posts = new Posts();
//        posts.setTitle(postsFormDto.getTitle());
//        posts.setBody(postsFormDto.getBody());
//
//        return posts;
//    }

    // Posts 업데이트 메소드
    public void updatePosts(PostsFormDto postsFormDto) {
        this.title = postsFormDto.getTitle();
        this.body = postsFormDto.getBody();
    }

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostsImg> postsImgList = new ArrayList<>();



}

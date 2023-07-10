package com.example.travel.entity;


import com.example.travel.dto.PostsFormDto;
import com.example.travel.dto.PostsImgFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="posts_img")
@Getter @Setter
public class PostsImg extends BaseTimeEntity{

    @Id
    @Column(name="posts_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName; // 이미지 파일명
    private String oriImgName; // 원본 이미지 파일명
    private String imgUrl; // 이미지 조회 경로

    //Posts 엔티티와 매핑
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 지연로딩
    @JoinColumn(name = "posts_id")
    private Posts posts;

    public void createPostsImg(String imgName, String oriImgName, String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }


    public void updatePostsImg(String imgName, String oriImgName, String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }

}

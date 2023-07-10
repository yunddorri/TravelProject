package com.example.travel.dto;

import com.example.travel.entity.BaseTimeEntity;
import com.example.travel.entity.Posts;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class PostsFormDto extends BaseTimeEntity {
    private Long id;
    private String title; // 제목
    private String body; // 내용

    public List<PostsImgFormDto> postsImgFormDtoList = new ArrayList<>();

    public List<Long> postsImgIds = new ArrayList<>();
    // ModelMapper(Entity와 Dto)
    private static ModelMapper modelMapper = new ModelMapper();

    public Posts createPosts() {
        return modelMapper.map(this, Posts.class);
    }
    public static PostsFormDto of(Posts posts) {
        return modelMapper.map(posts, PostsFormDto.class);
    }



}

package com.example.travel.dto;

import com.example.travel.entity.BaseTimeEntity;
import com.example.travel.entity.Posts;
import com.example.travel.entity.PostsImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class PostsImgFormDto extends BaseTimeEntity {
    private Long id; // id

    private String imgName; // 이미지 파일명
    private String oriImgName; // 원본 이미지 파일명
    private String imgUrl; // 이미지 조회 경로

    // ModelMapper(Entity와 Dto)
    private static ModelMapper modelMapper = new ModelMapper();

    public static PostsImgFormDto of(PostsImg postsImg) {
        return modelMapper.map(postsImg, PostsImgFormDto.class);
    }

}

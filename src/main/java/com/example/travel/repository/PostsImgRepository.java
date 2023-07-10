package com.example.travel.repository;

import com.example.travel.entity.PostsImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsImgRepository extends JpaRepository<PostsImg, Long> {
    List<PostsImg> findByPostsIdOrderByIdAsc(Long postsId);
}

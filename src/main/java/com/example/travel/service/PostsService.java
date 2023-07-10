package com.example.travel.service;

import com.example.travel.dto.PostsFormDto;
import com.example.travel.dto.PostsImgFormDto;
import com.example.travel.entity.Posts;
import com.example.travel.entity.PostsImg;
import com.example.travel.repository.PostsImgRepository;
import com.example.travel.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostsImgRepository postsImgRepository;
    private final PostsImgService postsImgService;

    // 글 등록
    public Long createPosts(PostsFormDto postsFormDto, List<MultipartFile> postsImgFileList) throws Exception{
        Posts posts = postsFormDto.createPosts();
        postsRepository.save(posts);

        // 이미지 등록
        for (int i= 0; i < postsImgFileList.size(); i++) {
            PostsImg postsImg = new PostsImg();
            postsImg.setPosts(posts);

            postsImgService.createPostsImg(postsImg, postsImgFileList.get(i));
        }

        return posts.getId();
    }

    // 글 조회 (상세 조회)
//    @Transactional(readOnly = true)
//    public PostsFormDto getPostsDtl(Long postsId) {
//        Posts posts = postsRepository.findById(postsId)
//                .orElseThrow(EntityNotFoundException::new);
//        PostsFormDto postsFormDto = PostsFormDto.of(posts);
//        return postsFormDto;
//    }

    @Transactional(readOnly = true)
    public PostsFormDto getPostsDtl(Long postsId) {
        List<PostsImg> postsImgList = postsImgRepository.findByPostsIdOrderByIdAsc(postsId);

        List<PostsImgFormDto> postsImgFormDtoList = new ArrayList<>();
        for(PostsImg postsImg : postsImgList) {
            PostsImgFormDto postsImgFormDto = PostsImgFormDto.of(postsImg);
            postsImgFormDtoList.add(postsImgFormDto);
        }

        Posts posts = postsRepository.findById(postsId).orElseThrow(EntityNotFoundException::new);
        PostsFormDto postsFormDto = PostsFormDto.of(posts);

        postsFormDto.setPostsImgFormDtoList(postsImgFormDtoList);
        return postsFormDto;
    }


    // 글 조회 (전체 조회)
//    @Transactional(readOnly = true)
//    public List<PostsFormDto> getPostsList() {
//        List<Posts> postsList = postsRepository.findAll();
//        List<PostsFormDto> postsFormDtoList = new ArrayList<>();
//
//        for(Posts posts : postsList) {
//            PostsFormDto postsFormDto = new PostsFormDto();
//            postsFormDto.setId(posts.getId());
//            postsFormDto.setTitle(posts.getTitle());
//            postsFormDto.setBody(posts.getBody());
//            postsFormDtoList.add(postsFormDto);
//        }
//        return postsFormDtoList;
//    }
    @Transactional(readOnly = true)
    public List<PostsFormDto> getPostsList() {
        List<PostsImg> postsImgList = postsImgRepository.findAll();

        List<PostsImgFormDto> postsImgFormDtoList = new ArrayList<>();
        for(PostsImg postsImg : postsImgList) {
            PostsImgFormDto postsImgFormDto = PostsImgFormDto.of(postsImg);
            postsImgFormDtoList.add(postsImgFormDto);
        }

        List<Posts> postsList = postsRepository.findAll();
        List<PostsFormDto> postsFormDtoList = new ArrayList<>();
        for(Posts posts : postsList) {
            PostsFormDto postsFormDto = PostsFormDto.of(posts);
            postsFormDto.setPostsImgFormDtoList(postsImgFormDtoList);
            postsFormDtoList.add(postsFormDto);
        }


        return postsFormDtoList;
    }


//    // 글 수정
//    public Long updatePosts(PostsFormDto postsFormDto) {
//        Posts posts = postsRepository.findById(postsFormDto.getId()).orElseThrow(EntityNotFoundException::new);
//        posts.updatePosts(postsFormDto);
//
//        return posts.getId();
//    }

    // 글 수정
    public Long updatePosts(PostsFormDto postsFormDto, List<MultipartFile> postsImgFileList) throws Exception {
        Posts posts = postsRepository.findById(postsFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        posts.updatePosts(postsFormDto);

        List<Long> postsImgIds = postsFormDto.getPostsImgIds();
        for(int i=0; i< postsImgFileList.size(); i++)
            postsImgService.updatePostsImg(postsImgIds.get(i), postsImgFileList.get(i));

        return posts.getId();
    }



    // 글 삭제
    @Transactional
    public void deletePosts(Long postsId) {
        postsRepository.deleteById(postsId);
    }
//    @Transactional
//    public void deletePosts(Long postsId) throws Exception {
////        Posts posts = postsRepository.findById(postsId)
////                .orElseThrow(EntityNotFoundException::new);
////
////        // Posts에 연결된 PostsImg 엔티티들 삭제
////        List<PostsImg> postsImgList = posts.getPostsImgList();
////        for (PostsImg postsImg : postsImgList) {
////            postsImgService.deletePostsImg(postsImg.getId());
////        }
////
////        // Posts 엔티티 삭제
////        postsRepository.delete(posts);
//
//        Posts posts = postsRepository.findById(postsId)
//                .orElseThrow(EntityNotFoundException::new);
//
//        // 복사한 Posts 객체 생성
//        Posts copiedPosts = new Posts();
//        BeanUtils.copyProperties(posts, copiedPosts);
//
//        // Posts에 연결된 PostsImg 엔티티들 삭제
//        List<PostsImg> postsImgList = copiedPosts.getPostsImgList();
//        for (PostsImg postsImg : postsImgList) {
//            postsImgService.deletePostsImg(postsImg.getId());
//        }
//
//        // Posts 엔티티 삭제
//        postsRepository.delete(posts);
//    }


}

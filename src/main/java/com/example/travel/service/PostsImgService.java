package com.example.travel.service;

import com.example.travel.dto.PostsFormDto;
import com.example.travel.entity.Posts;
import com.example.travel.entity.PostsImg;
import com.example.travel.repository.PostsImgRepository;
import com.example.travel.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsImgService {

    // application.properties 파일에 등록한 값 저장하려고
    @Value("${postsImgLocation}")
    private String postsImgLocation;

    private final PostsImgRepository postsImgRepository;
    private final FileService fileService;

    //private final PostsRepository postsRepository;

    // 이미지 등록
    public void createPostsImg(PostsImg postsImg, MultipartFile postsImgFile) throws Exception {
        // 원래 파일명 받기
        String oriImgName = postsImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 등록한 파일 이름 저장, 이미지 불러올 경로 설정

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(postsImgLocation, oriImgName, postsImgFile.getBytes());
            imgUrl = "/images/posts/" + imgName;
        }

        // 입력받은 이미지 정보 저장
        postsImg.createPostsImg(imgName, oriImgName, imgUrl);
        postsImgRepository.save(postsImg);

    }

    // 이미지 수정
    public void updatePostsImg(Long postsImgId, MultipartFile postsImgFile) throws Exception {
        if(!postsImgFile.isEmpty()){
            PostsImg savedPostsImg = postsImgRepository.findById(postsImgId).orElseThrow(EntityNotFoundException::new);
            if(!StringUtils.isEmpty(savedPostsImg.getImgName())){
                fileService.deleteFile(postsImgLocation+"/"+savedPostsImg.getImgName());
            }
            String oriImgName = postsImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(postsImgLocation, oriImgName, postsImgFile.getBytes());
            String imgUrl = "/images/posts/" + imgName;

            savedPostsImg.updatePostsImg(imgName, oriImgName, imgUrl);
        }
    }

//    @Transactional
//    public void updatePostsImg(PostsFormDto postsFormDto, List<MultipartFile> postsImgFileList) throws Exception {
//        Posts posts = postsRepository.findById(postsFormDto.getId())
//                .orElseThrow(EntityNotFoundException::new);
//        posts.updatePosts(postsFormDto);
//
//        List<Long> postsImgIds = postsFormDto.getPostsImgIds();
//        List<PostsImg> existingPostsImgList = postsImgRepository.findByPostsIdOrderByIdAsc(posts.getId());
//
//        // 기존 첨부 파일 삭제
//        for (int i = 0; i < existingPostsImgList.size(); i++) {
//            PostsImg existingPostsImg = existingPostsImgList.get(i);
//            if (i < postsImgFileList.size()) {
//                MultipartFile newPostsImgFile = postsImgFileList.get(i);
//                if (!newPostsImgFile.isEmpty()) {
//                    // 새로운 파일이 있는 경우, 파일 업로드 및 기존 파일 삭제
//                    fileService.deleteFile(postsImgLocation + "/" + existingPostsImg.getImgName());
//                    String oriImgName = newPostsImgFile.getOriginalFilename();
//                    String imgName = fileService.uploadFile(postsImgLocation, oriImgName, newPostsImgFile.getBytes());
//                    String imgUrl = "/images/posts/" + imgName;
//                    existingPostsImg.updatePostsImg(imgName, oriImgName, imgUrl);
//                }
//            } else {
//                // 새로운 파일이 없는 경우, 기존 파일 삭제
//                fileService.deleteFile(postsImgLocation + "/" + existingPostsImg.getImgName());
//                postsImgRepository.delete(existingPostsImg);
//            }
//        }
//
//        // 추가적으로 첨부된 파일이 있는 경우, 업로드 처리
//        for (int i = existingPostsImgList.size(); i < postsImgFileList.size(); i++) {
//            MultipartFile newPostsImgFile = postsImgFileList.get(i);
//            if (!newPostsImgFile.isEmpty()) {
//                PostsImg newPostsImg = new PostsImg();
//                newPostsImg.setPosts(posts);
//                String oriImgName = newPostsImgFile.getOriginalFilename();
//                String imgName = fileService.uploadFile(postsImgLocation, oriImgName, newPostsImgFile.getBytes());
//                String imgUrl = "/images/posts/" + imgName;
//                newPostsImg.createPostsImg(imgName, oriImgName, imgUrl);
//                postsImgRepository.save(newPostsImg);
//            }
//        }
//    }

    // 글 삭제
//    public void deletePostsImg(Long postsImgId) throws Exception {
////        PostsImg postsImg = postsImgRepository.findById(postsImgId).orElseThrow(EntityNotFoundException::new);
////        // Posts 엔티티에서 해당 PostsImg 엔티티 제거
////        Posts posts = postsImg.getPosts();
////        posts.getPostsImgList().remove(postsImg);
////
////        fileService.deleteFile(postsImgLocation+"/"+postsImg.getImgName());
////        // PostsImg 엔티티 삭제
////        postsImgRepository.delete(postsImg);
//
//
////        PostsImg postsImg = postsImgRepository.findById(postsImgId)
////                .orElseThrow(EntityNotFoundException::new);
////
////        // Posts 엔티티에서 해당 PostsImg 엔티티 제거
////        Posts posts = postsImg.getPosts();
////        posts.getPostsImgList().remove(postsImg);
////        postsImg.setPosts(null);
////
////        fileService.deleteFile(postsImgLocation + "/" + postsImg.getImgName());
////
////        // PostsImg 엔티티 삭제
////        postsImgRepository.delete(postsImg);
//    }


}

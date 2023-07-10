package com.example.travel.controller;

import com.example.travel.dto.PostsFormDto;
import com.example.travel.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;

    // 글 등록 - GET
    @GetMapping(value="posts/create")
    public String postsForm(Model model) {
        model.addAttribute("postsFormDto", new PostsFormDto()); // postsFormDto 라는 이름으로 넘겨줌
        return "posts/postsForm";
    }

    // 글 등록 - POST
    @PostMapping(value="posts/create")
    public String postsForm(PostsFormDto postsFormDto, @RequestParam("postsImgFile") List<MultipartFile> postsImgFileList, Model model) {
        //postsService.createPosts(postFormDto);
        try{
            postsService.createPosts(postsFormDto, postsImgFileList);
        }catch (Exception e){
            model.addAttribute("err", "글 게시 중 에러가 발생했습니다.");
            return "posts/postsForm";
        }
        return "redirect:/"; // main 페이지로
    }

    // 세부 글 조회 - GET
    @GetMapping(value="posts/{postsId}")
    public String postsDtl(Model model, @PathVariable("postsId") Long postsId) {
        PostsFormDto postsFormDto = postsService.getPostsDtl(postsId);
        model.addAttribute("posts", postsFormDto); // posts 라는 이름으로 넘겨줌
        return "posts/postsDtl";
    }

    // 전체 글 조회 - GET
    @GetMapping(value="posts/")
    public String postsList(Model model) {
        List<PostsFormDto> postsFormDtoList = postsService.getPostsList();
        model.addAttribute("postsList", postsFormDtoList); // postsList 이름으로 넘겨줌
        return "posts/postsList";
    }

    // 글 수정 -GET (수정할 때 이전에 작성된 내용들 보여주기 위해)
    @GetMapping(value="posts/{postsId}/update")
    public String updatePostsDtl(Model model, @PathVariable("postsId") Long postsId) {
        PostsFormDto postsFormDto = postsService.getPostsDtl(postsId);
        model.addAttribute("posts", postsFormDto); // posts 라는 이름으로 넘겨줌
        return "posts/postsUpdate";
    }

    // 글 수정 - POST 가 아니라 PUT
    @PutMapping(value="posts/{postsId}/update")
    //public @ResponseBody ResponseEntity updatePosts(@PathVariable("postsId") Long postsId){
    public String updatePosts(PostsFormDto postsFormDto, @RequestParam("postsImgFile") List<MultipartFile> postsImgFileList, Model model){
        //postsService.updatePosts(postsFormDto);

        //postsService.createPosts(postsFormDto);
        try{
            postsService.updatePosts(postsFormDto, postsImgFileList);
        }catch (Exception e){
            model.addAttribute("err", "글 수정 중 에러가 발생했습니다.");
            return "posts/postsForm";
        }
        return "redirect:/";
    }

    // 글 삭제
    @DeleteMapping("/posts/{postsId}")
    public String deletePosts(@PathVariable("postsId") Long postsId, Model model) {
        try{
            postsService.deletePosts(postsId);
        }catch (Exception e){
            model.addAttribute("err", "글 삭제 중 에러가 발생했습니다.");
            return "posts/postsDtl";
        }

        return "redirect:/";
    }

}

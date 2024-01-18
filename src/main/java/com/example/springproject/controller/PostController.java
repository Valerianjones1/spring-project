package com.example.springproject.controller;

import com.example.springproject.entity.Post;
import com.example.springproject.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class PostController {
    private final PostService postService;

    @GetMapping
    public String viewBlogPage(Model model) {
        List<Post> listPosts = postService.listAll();
        model.addAttribute("listPosts", listPosts);
        return "blog";
    }

    @GetMapping("/new")
    public String showNewPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "new_post";
    }

    @PostMapping("/save")
    public String savePost(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model) {
        boolean isThereAreErrors = result.hasErrors();
        System.out.println(post);
        if (isThereAreErrors) {
            System.out.println(post);
            if (post.getId() != null) {
                model.addAttribute("post", post);
                return "edit_post";
            }
            System.out.println(post);
            model.addAttribute("post", post);
            return "new_post";
        }
        System.out.println(post);
        postService.save(post);
        return "redirect:/blog";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditPostForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_post");
        Post post = postService.get(id);
        mav.addObject("post", post);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id) {
        postService.delete(id);
        return "redirect:/blog";
    }
}

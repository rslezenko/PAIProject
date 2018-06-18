package com.rslezenko.web;

import com.rslezenko.model.Post;
import com.rslezenko.model.User;
import com.rslezenko.repository.UserRepository;
import com.rslezenko.service.PostService;
import com.rslezenko.service.UserService;
import com.rslezenko.web.dto.PostDTO;
import com.rslezenko.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository rep;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
/*
    @GetMapping("/user")
    public String userIndex() {
        return "user";
    }
     */

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/profile")
    public String user(Model model) {

        List<Post> Posts = postService.findAll();
        model.addAttribute("posts", Posts);
        return "user";
    }

    /*@GetMapping("/edit")
    public String edit() {
        return "edit";
    }
    */

    @GetMapping("/user")
    public String userr(Model model) {

        List<Post> Posts = postService.findAll();
        model.addAttribute("posts", Posts);

        return "user";
    }

    @GetMapping("/newpost")
    public String post() {
        return "newpost";
    }

    @ModelAttribute("post")
    public PostDTO PostDTO() {
        return new PostDTO();
    }

    @PostMapping("/newposts")
    public String newpost( @ModelAttribute("post") @Valid PostDTO postDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()){
            return "redirect:/newpost?error";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setAuthor(name);

        Post p = postService.create(post);

        return "redirect:/newpost?success";
    }




    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        List<Post> Posts = postService.findAll();
        model.addAttribute("posts", Posts);

        return "admin";
    }

    @RequestMapping("/admin/delete/{id}")
    public String view(@PathVariable("id") int id, Model model) {
        rep.deleteByIdRole(id);
        rep.deleteById(id);

        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        return "admin";
    }

    @RequestMapping("/admin/delete/post/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
         postService.deleteById(id);

        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        List<Post> Posts = postService.findAll();
        model.addAttribute("posts", Posts);
        return "admin";
    }

    @GetMapping("/editUser")
    public String edit( Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("user", user);


        return "editUser";
    }




    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("userr")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @PostMapping("/edit/{id}")
    public String save(@PathVariable("id") Integer id, @ModelAttribute("userr") @Valid UserRegistrationDto userDto, BindingResult result) {





        if (result.hasErrors()){
            return "redirect:/users?error";
        }

        rep.setUserInfoById(userDto.getFirstName(),userDto.getLastName(),userDto.getUsername()
                ,passwordEncoder.encode(userDto.getPassword()),userDto.getEmail(),userDto.getDao(),userDto.getNumberNum(),id);
        return "redirect:/users?success";

    }


    @RequestMapping("/users")
    public String index(Model model) {
        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        return "users";
    }
}

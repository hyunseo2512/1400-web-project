package com.community.demo.controller;

import com.community.demo.dto.UserDTO;
import com.community.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user/*")
@Slf4j
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public void join() {}

    @GetMapping("/login")
    public void login(HttpServletRequest request,
                      Model model){
        String email = (String)request.getSession().getAttribute("email");
        String errMsg = (String)request.getSession().getAttribute("errMsg");
        if(errMsg != null){
            log.info(">>> errMsg >> {}",errMsg);
            model.addAttribute("email", email);
            model.addAttribute("errMsg", errMsg);
        }
        request.getSession().removeAttribute("email");
        request.getSession().removeAttribute("errMsg");
    }

    @PostMapping("/join")
    public String join(UserDTO userDTO){
        String email = userService.register(userDTO);
        log.info(">>> email >> {}", email);
        return "redirect:/";
    }

    @GetMapping("/modify")
    public void modify(){}

    @GetMapping("/list")
    public void list(Model model){
        List<UserDTO> userList = userService.getList();
        model.addAttribute("userList", userList);
    }

    @GetMapping("password")
    public void password() {}
}

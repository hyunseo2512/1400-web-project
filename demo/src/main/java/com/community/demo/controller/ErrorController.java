package com.community.demo.controller;

import com.community.demo.dto.BoardDTO;
import com.community.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/error/*")
public class ErrorController {
    private final BoardService boardService;

    @GetMapping("/401")
    public void error401(){}

    @GetMapping("/404")
    public void error404(){}

    @GetMapping("/500")
    public void error500(){}
}


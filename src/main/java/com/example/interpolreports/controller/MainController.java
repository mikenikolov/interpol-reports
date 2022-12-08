package com.example.interpolreports.controller;

import com.example.interpolreports.model.Notice;
import com.example.interpolreports.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainController {
    private RequestService requestService;

    @GetMapping("/red")
    public String getAllRedNotices(Model model) {
        model.addAttribute("category", "Red Notices");
        model.addAttribute("persons", requestService.doRequest(Notice.RED));
        return "index";
    }

    @GetMapping("/yellow")
    public String getAllYellowNotices(Model model) {
        model.addAttribute("category", "Yellow Notices");
        model.addAttribute("persons", requestService.doRequest(Notice.YELLOW));
        return "index";
    }

    @RequestMapping
    public String index() {
        return "index";
    }
}

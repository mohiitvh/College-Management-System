package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.NoticeEntity;
import com.example.demo.service.NoticeService;

@Controller
@RequestMapping("/admin/notices")
public class AdminNoticeController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/add")
    public String addForm(Model model){

        model.addAttribute(
                "notice",
                new NoticeEntity());

        return "Admin/add-notice";
    }


    @PostMapping("/save")
    public String save(
            @ModelAttribute NoticeEntity notice){

        noticeService.save(notice);

        return "redirect:/admin/notices/list";

    }


    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute(
                "notices",
                noticeService.getAll());

        return "Admin/notice-list";

    }
    
    @GetMapping("/view/{id}")
    public String viewNotice(
            @PathVariable Long id,
            Model model){

        model.addAttribute(
                "notice",
                noticeService.findById(id));

        return "Admin/add-notice";

    }
    @GetMapping("/edit/{id}")
    public String editNotice(
            @PathVariable Long id,
            Model model){

        model.addAttribute(
                "notice",
                noticeService.findById(id));

        return "Admin/add-notice";

    }
    @GetMapping("/delete/{id}")
    public String deleteNotice(
            @PathVariable Long id){

        noticeService.delete(id);

        return "redirect:/admin/notices/list";

    }
}
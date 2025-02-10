package com.example.demo.Controller;

import com.example.demo.Domain.Friend;
import com.example.demo.Service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/friends")
@RequiredArgsConstructor
@Slf4j
public class FriendController {
    private final FriendService friendService;

    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("friends", friendService.findAllFriend());
        return "friends/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("friend", new Friend());

        return "friends/addform";
    }

    @PostMapping("/add")
    public String addFriend(@ModelAttribute("friend") Friend friend){
        friendService.save(friend);
        return "redirect:/friends/list";
    }

    @GetMapping("/{id}")
    public String detailFriend(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("friend", friendService.findFriendById(id));
        return "friends/detail";
    }

    @PostMapping("/delete")
    public String deleteFriend(@RequestParam("id") Long id){
        friendService.deleteFriendById(id);
        return "redirect:/friends/list";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") Long id, Model model){
        model.addAttribute("friend", friendService.findFriendById(id));
        return "friends/update";
    }
}

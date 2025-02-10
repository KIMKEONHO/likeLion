package com.example.boardproject.controller;

import com.example.boardproject.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.boardproject.service.BoardService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    public final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "currentSize", required = false, defaultValue = "5") int size) {

        model.addAttribute("boards", boardService.getBoards(PageRequest.of(page -1 ,size)));
        model.addAttribute("page", page);

        return "board/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(name = "id", required = false) Long id, Model model) {
        model.addAttribute("boardDetail", boardService.findBoardById(id) );
        return "board/detail";
    }

    @GetMapping("/writeform")
    public String writeForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/writeform";
    }

    @PostMapping("/writeform")
    public String addBoard(@ModelAttribute("board") Board board) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(board.getPassword());
        board.setPassword(hashedPassword);
        boardService.createBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id", required = false) Long id, Model model) {
        model.addAttribute("board", boardService.findBoardById(id));
        return "board/delete";
    }

    @PostMapping("/delete")
    public String deleteFriend(@ModelAttribute("board") Board board, RedirectAttributes redirectAttributes){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Board real = boardService.findBoardById(board.getId());

        String storedHash = real.getPassword();
        String rawPassword = board.getPassword();

        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, storedHash);
        if (isPasswordMatch) {
            board.setPassword(storedHash);
            boardService.deleteBoardById(board.getId());
            return "redirect:/board/list";
        } else {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/board/delete?id=" + board.getId();
        }
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") Long id, Model model){
        System.out.println(boardService.findBoardById(id).getCreatedAt());
        model.addAttribute("board", boardService.findBoardById(id));
        return "board/update";
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute("board") Board board, RedirectAttributes redirectAttributes){
        System.out.println(board.getCreatedAt());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Board real = boardService.findBoardById(board.getId());

        String storedHash = real.getPassword();
        String rawPassword = board.getPassword();

        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, storedHash);
        if (isPasswordMatch) {
            board.setPassword(storedHash);
            boardService.updateBoard(board);
            return "redirect:/board/list";
        } else {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/board/update?id=" + board.getId();
        }
    }

}

package com.example.boardproject.service;

import com.example.boardproject.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.boardproject.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by(Sort.Direction.ASC,"id")));
    }

    public Board findBoardById(long id){
        return boardRepository.findById(id).orElse(null);
    }

    public void createBoard(Board board){
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoardById(Long id){
        boardRepository.deleteById(id);
    }

    public void updateBoard(Board board){
        boardRepository.save(board);
    }
}

package com.example.boardproject.service;

import com.example.boardproject.domain.BoardUser;
import com.example.boardproject.repository.BoardUserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Service
@RequiredArgsConstructor
public class LoginService {
    private final BoardUserRepository boardUserRepository;

    private String loggedInUserId; // 로그인한 사용자 ID
    private Long seq;  // db에 저장된 사용자의 키값

    public boolean login(String userId, String password) {
        BoardUser user =  boardUserRepository.findByUserId(userId);
        if(user == null) {
            return false;
        }else{
            if(user.getUser_password().equals(password))
            {
                loggedInUserId = user.getUser_id();
                seq = user.getId();
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean signup(BoardUser user){
        if(checkId(user.getUser_id())){
            boardUserRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean checkId(String userId) {
        BoardUser user = boardUserRepository.findByUserId(userId);
        if(user == null) {
            return true;
        }
        return false;
    }
}

package com.example.demo.Service;

import com.example.demo.Domain.Friend;
import com.example.demo.Repository.FreindRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FreindRepository freindRepository;

    // select는 트랜잭션 관리 안해도 된다. readOnly를 추가함으로 성능을 조금 개선 가능
    @Transactional(readOnly = true)
    public Iterable<Friend> findAllFriend() {
        return freindRepository.findAll();
    }

    public Friend save(Friend friend){
        // Spring Data에는 save (반드시 insert만 실행되는 것은 아니다.
        // id 값에 이미 존재하고 있다면 .. 수정, id가 없다면 생성

        return freindRepository.save(friend);
    }

    public Friend findFriendById(long id){
        return freindRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public void deleteFriendById(long id){
        freindRepository.deleteById(id);
    }
}

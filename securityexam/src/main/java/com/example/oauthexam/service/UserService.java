package com.example.oauthexam.service;

import com.example.oauthexam.dto.SocialUserRequestDto;
import com.example.oauthexam.entity.User;
import com.example.oauthexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User saveUser(SocialUserRequestDto requestDto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setSocialId(requestDto.getSocialId());
        user.setProvider(requestDto.getProvider());
        user.setPassword(passwordEncoder.encode(""));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByProviderAndSocialId(String provider,String socialId) {
        return userRepository.findByProviderAndSocialId(provider, socialId);
    }
}

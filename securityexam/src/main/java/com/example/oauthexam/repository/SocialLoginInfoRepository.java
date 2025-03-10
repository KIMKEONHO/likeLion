package com.example.oauthexam.repository;

import com.example.oauthexam.entity.SocialLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialLoginInfoRepository extends JpaRepository<SocialLoginInfo, Long> {
    Optional<SocialLoginInfo> findByProvierAndUuidAndSocialId(String provider, String uuid, String socialId);
}

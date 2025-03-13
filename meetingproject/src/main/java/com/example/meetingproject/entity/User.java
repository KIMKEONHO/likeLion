package com.example.meetingproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, name = "username")
    private String username;

    @Column(nullable = false, length = 100, name = "password")
    private String password;

    @Column(nullable = false, length = 100, unique = true, name = "email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MeetingParticipant> meetingParticipants;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ScheduleParticipant> scheduleParticipants;

}

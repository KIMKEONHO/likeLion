package com.example.meetingproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter@Setter
@Table(name = "meetings")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "maxParticipants", columnDefinition = "INT DEFAULT 10")
    private Integer maxParticipants = 10;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creater;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingParticipant> meetingparticipants;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}

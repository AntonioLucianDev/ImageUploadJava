package com.asusoftware.ImageUpload.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "local_path")
    private String localPath;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}

package com.jeonguk.web.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}
}

package com.jeonguk.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

public class PostDTO {

	@Data
	public static class ReqPost {
		private String title;
		private String content;
	}

	@Data
	public static class ResPost {
		private Long id;
		private String title;
		private String content;
		@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
		private LocalDateTime createdAt;
	}

}

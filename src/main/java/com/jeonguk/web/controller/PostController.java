package com.jeonguk.web.controller;

import com.jeonguk.web.config.annotation.ApiVersion;
import com.jeonguk.web.dto.PostDTO;
import com.jeonguk.web.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
@ApiVersion
public class PostController {

	private final RestTemplate restTemplate;
	private final PostService postService;

	@GetMapping("/{id}")
	PostDTO.ResPost getPost(@PathVariable("id") Long id) {
		return postService.getPost(id);
	}

	@GetMapping
	List<PostDTO.ResPost> getPostAll() {
		return postService.getPostAll();
	}

	@PostMapping
	PostDTO.ResPost savePost(@RequestBody @Valid PostDTO.ReqPost reqPost) {
		return postService.savePost(reqPost);
	}

}

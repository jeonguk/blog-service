package com.jeonguk.web.service.impl;

import com.jeonguk.web.repository.PostRepository;
import com.jeonguk.web.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

}

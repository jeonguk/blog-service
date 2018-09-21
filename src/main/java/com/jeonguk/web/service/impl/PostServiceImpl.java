package com.jeonguk.web.service.impl;

import com.jeonguk.web.dto.PostDTO;
import com.jeonguk.web.entity.Post;
import com.jeonguk.web.exception.CustomException;
import com.jeonguk.web.exception.CustomStatus;
import com.jeonguk.web.repository.PostRepository;
import com.jeonguk.web.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

	private final ModelMapper modelMapper;
	private final PostRepository postRepository;

	@Override
	public PostDTO.ResPost getPost(Long id) {
		final Optional<Post> post = postRepository.findById(id);
		return post.map(p -> modelMapper.map(p, PostDTO.ResPost.class)).orElseThrow(() -> new CustomException(CustomStatus.RESOURCE_NOT_FOUND));
	}

	@Override
	public List<PostDTO.ResPost> getPostAll() {
		final List<Post> postList = postRepository.findAll();
		return ListUtils.emptyIfNull(postList).stream().map(post -> modelMapper.map(post, PostDTO.ResPost.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public PostDTO.ResPost savePost(PostDTO.ReqPost request) {
		final Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setUserName(request.getUserName());
		return modelMapper.map(postRepository.save(post), PostDTO.ResPost.class);
	}
}

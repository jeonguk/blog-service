package com.jeonguk.web.service;

import com.jeonguk.web.dto.PostDTO;

import java.util.List;

public interface PostService {
	PostDTO.ResPost getPost(Long postId);
	PostDTO.ResPost savePost(PostDTO.ReqPost post);
	List<PostDTO.ResPost> getPostAll();
}

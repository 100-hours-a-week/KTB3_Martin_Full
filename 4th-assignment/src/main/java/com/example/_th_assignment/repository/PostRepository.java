package com.example._th_assignment.repository;

import com.example._th_assignment.dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final HashMap<Long, PostDto> postmap;

    private long sequence;

    public PostRepository() {
        postmap = new HashMap<>();
        sequence = 0L;

        save(new PostDto("내가 어제 먹은것", "감자"));
        save(new PostDto("내가 오늘 먹은것", "고구마"));
        save(new PostDto("내가 내일 먹을것", "칼국수"));
    }


    public List<PostDto> getAllList(){
        ArrayList<PostDto> list = new ArrayList<>();
        for(PostDto post : postmap.values()){
            if(!post.getIsdeleted()) list.add(post);
        }
        return list;
    }
    public Optional<PostDto> getbyId(long id) {
        return Optional.ofNullable(postmap.get(id)).filter(post -> !post.getIsdeleted());
    }

    public PostDto save(PostDto postDto) {
        postDto.setId(++sequence);
        postmap.put(sequence, postDto);
        return postDto;
    }
    public PostDto update(Long id, PostDto postDto) {
        postmap.replace(id, postDto);
        return postmap.get(id);
    }

    public void delete(long id) {
        PostDto postDto = getbyId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        postDto.setIsdeleted(true);
    }





}

package com.example._th_assignment.Repository;

import com.example._th_assignment.Dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final LinkedHashMap<Long, PostDto> postStore;

    private final AtomicLong sequence;

    public PostRepository() {
        postStore = new LinkedHashMap<>();
        sequence = new AtomicLong(0);

        save(new PostDto("foo@bar","내가 어제 먹은것", "감자"));
        save(new PostDto("foo@bar","내가 오늘 먹은것", "고구마"));
        save(new PostDto("foo@bar","내가 내일 먹을것", "칼국수"));
    }


    public List<PostDto> getAllPosts(){
        ArrayList<PostDto> list = new ArrayList<>();
        for(PostDto post : postStore.values()){
            if(!post.getIsdeleted()) list.add(post);
        }
        return list;
    }
    public Optional<PostDto> getbyId(long id) {
        return Optional.ofNullable(postStore.get(id)).filter(post -> !post.getIsdeleted());
    }

    public PostDto save(PostDto postDto) {
        postDto.setId(sequence.incrementAndGet());
        String timeStamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss"));
        postDto.setBirthtime(timeStamp);
        postStore.putFirst(sequence.get(), postDto);
        return postDto;
    }
    public PostDto update(Long id, PostDto postDto) {
        PostDto post = getbyId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"post not found"));
        if(post.getIsdeleted())
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "post not found");

        postStore.replace(postDto.getId(), postDto);
        return postStore.get(id);
    }

    public void delete(long id) {
        PostDto postDto = getbyId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post not found"));
        postDto.setIsdeleted(true);
    }

    public long count(){
        return sequence.get();
    }







}

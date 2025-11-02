package com.example._th_assignment.Repository;

import com.example._th_assignment.Entity.AppUserEntity;
import com.example._th_assignment.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<PostEntity,Long> {

    public Optional<PostEntity> findByid(PostEntity post);
}

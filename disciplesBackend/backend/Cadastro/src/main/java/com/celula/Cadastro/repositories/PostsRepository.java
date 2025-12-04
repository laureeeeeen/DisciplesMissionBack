package com.celula.Cadastro.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.celula.Cadastro.entities.Posts;


@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

}

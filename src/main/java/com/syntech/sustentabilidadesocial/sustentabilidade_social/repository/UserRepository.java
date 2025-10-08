package com.syntech.sustentabilidadesocial.sustentabilidade_social.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByEmail(String email); // busca de usuário opcional pelo email...

    Optional<User> findBySlug(String slug);
}

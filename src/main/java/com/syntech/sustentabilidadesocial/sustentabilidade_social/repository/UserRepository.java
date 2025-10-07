package com.syntech.sustentabilidadesocial.sustentabilidade_social.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syntech.sustentabilidadesocial.sustentabilidade_social.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
}

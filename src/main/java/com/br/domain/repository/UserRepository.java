package com.br.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, 
		JpaSpecificationExecutor<User>, UserRepositoryQuery{

	@Query("FROM User WHERE matricula = :matricula")
	Optional<User> findByMatriculaSearch(String matricula);

	Optional<User> findByEmail(String email);

	@EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
	Optional<User> findById(Long userId);

	@EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.FETCH)
	Optional<User> findByMatricula(String matricula);

	@Query("FROM User WHERE departmentId = :departmentId")
	List<User> buscarUsuariosDoDepartamento(Long departmentId);

	@Query("FROM User WHERE matricula = :matricula AND password = :password")
	Optional<User> findByMatriculaAndPassword(@Param("matricula") String matricula, @Param("password") String password);
	
}

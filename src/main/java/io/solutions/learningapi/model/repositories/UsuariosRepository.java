package io.solutions.learningapi.model.repositories;

import io.solutions.learningapi.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 Author: Silas Santos Leite
 Date Created:: 2022-12-23

 @Repository: Diz ao Spring Boot que essa interface é uma especificação que traz os métodos necessarios
 para realizar a manipulação da tabela usuario

 Ao extender (extends) a JpaRepository deve ser definido a entidade que será mapeada e o tipo do Id que essa classe terá.

 Proximas classes que serão estudadas: UsuariosService e UsuariosServiceImpl
*/

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    /*
        @Query: Escreve um comando SQL, no exemplos abaixo está sendo usado o SQL usado pelo JPA,
        entretanto é possivel usar o SQL tradicional.
        @Param: Define o atributo que será passado como parâmetro no comando SQL na @Query
    */

    @Query("SELECT e FROM Usuario e JOIN FETCH e.perfis WHERE e.username = (:username)")
    public Usuario findByUsername(@Param("username") String username); // encontra o usuario usando o username

    @Query("SELECT e FROM Usuario e JOIN FETCH e.perfis WHERE e.email = (:email)")
    public Usuario findByEmail(@Param("email") String email); // encontra o usuario usando o email

    @Query("SELECT e FROM Usuario e JOIN FETCH e.perfis WHERE e.cpf = (:cpf)")
    public Usuario findByCpf(@Param("cpf") String cpf); // encontra o usuario usuando o cpf
}

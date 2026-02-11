package com.starfit.repository;

import com.starfit.model.Assinatura;
import com.starfit.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByUsuario(Usuario usuario);
    Optional<Assinatura> findByUsuarioAndStatus(Usuario usuario, Assinatura.StatusAssinatura status);
    List<Assinatura> findByStatus(Assinatura.StatusAssinatura status);
}






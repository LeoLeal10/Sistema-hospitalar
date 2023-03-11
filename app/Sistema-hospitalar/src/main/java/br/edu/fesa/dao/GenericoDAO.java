package br.edu.fesa.dao;

import br.edu.fesa.exception.PersistenciaException;
import java.io.Serializable;
import java.util.List;

public interface GenericoDAO<E> extends Serializable {

    List<E> Listar() throws PersistenciaException;

    void Inserir(E e) throws PersistenciaException;

    void Alterar(E e) throws PersistenciaException;

    void Remover(E e) throws PersistenciaException;
}

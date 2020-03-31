package model.dao;

import java.util.List;
import model.entities.Department;

public interface DepartmentDao {
    
    void insert(Department obj);	// inserir no BD
    void update(Department obj);	// atualizar no BD
    void deleteById(Integer id);	// deletar do BD por id
    Department findById(Integer id);	// encontrar no BD por id
    List<Department> findAll();		// listar todos os elementos

}

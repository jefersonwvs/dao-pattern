package model.dao;

import java.util.List;
import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
    
    void insert(Seller obj);				    // inserir no BD
    void update(Seller obj);				    // atualizar no BD
    void deleteById(Integer id);			    // deletar do BD por id
    Seller findById(Integer id);			    // encontrar no BD por id
    List<Seller> findByDepartment(Department department);   // listar os elementos do departamento department
    List<Seller> findAll();				    // listar todos os elementos
    
}

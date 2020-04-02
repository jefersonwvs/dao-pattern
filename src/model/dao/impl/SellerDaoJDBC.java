package model.dao.impl;

import db.DbException;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
	this.conn = conn;
    }
    
    @Override
    public void insert(Seller obj) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Seller obj) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seller findById(Integer id) {
	
	PreparedStatement st = null;
	ResultSet rs = null;
	
	try {
	    /* Comando SQL para busca por Id no vendedor */
	    st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
		    "FROM seller INNER JOIN department " +
		    "ON seller.DepartmentId = department.Id " +
		    "WHERE seller.Id = ?");
	    st.setInt(1, id);
	    rs = st.executeQuery();
	    
	    if (rs.next()) { // a primeira linha da tabela não é o resultado em si, pois compreende apenas os nomes dos atributos. O resultado da busca, se houver, será a segunda linha (rs.next())
		
		/*** extraindo os dados armazenados de acordo com o paradigma relacional 
		(tabela) e armazenando-os de acordo com o paradigma OO (objetos) ****/
		Department dep = instantiateDepartment(rs);
		Seller seller = instantiateSeller(rs, dep);
		
		return seller;
	    }
	    
	    return null; // rs.next(): segunda linha - pesquisou e não encontrou
	    
	} catch (SQLException ex) {
	    throw new DbException(ex.getMessage());
	} finally {
	    DB.closeStatement(st);
	    DB.closeResultSet(rs);
	}
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
	
	PreparedStatement st = null;
	ResultSet rs = null;
	
	try {
	    /* Comando SQL para busca por departamento*/
	    st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
		    "FROM seller INNER JOIN department " +
		    "ON seller.DepartmentId = department.Id " +
		    "WHERE DepartmentId = ? " +
		    "ORDER BY Name");
	    st.setInt(1, department.getId());
	    rs = st.executeQuery();
	    
	    List<Seller> list = new ArrayList<>();
	    
	    while (rs.next()) { // Normalmente haverá mais de um vendedor por departamento

		if (rs.getInt("DepartmentId") == department.getId()) {
		    department.setName(rs.getString("DepName"));    // na pesquisa não veio o nome
		    Seller seller = instantiateSeller(rs, department);
		    list.add(seller);
		}
	    }
	    
	    return list;
	    
	} catch (SQLException ex) {
	    throw new DbException(ex.getMessage());
	} finally {
	    DB.closeStatement(st);
	    DB.closeResultSet(rs);
	}

    }

    @Override
    public List<Seller> findAll() {
	
	PreparedStatement st = null;
	ResultSet rs = null;
	
	try {
	    /* Comando SQL para busca por departamento*/
	    st = conn.prepareStatement(
		    "SELECT seller.*,department.Name as DepName " +
		    "FROM seller INNER JOIN department " +
		    "ON seller.DepartmentId = department.Id " +
		    "ORDER BY Name");
	    rs = st.executeQuery();
	    
	    List<Seller> list = new ArrayList<>();
	    Map<Integer, Department> map = new HashMap<>(); 
	    /* a estrutura de dados Map não armazena dados repetidos. 
	    Sendo assim, ela funciona bem no contexto abaixo, onde tem-se
	    a seguinte situação: todos os funcionários de um mesmo departamento
	    tem de ser associados a uma única instância desse departamento, isto é,
	    não se pode ter duas ou mais instâncias de um departamento com o mesmo id*/
	    
	    
	    while (rs.next()) { // Normalmente haverá mais de um vendedor por departamento

		Department dep = map.get(rs.getInt("DepartmentId"));	// faz a pesquisas dos departamentos já vistos
		
		if (dep == null) {  // departamento ainda não visto
		    dep = instantiateDepartment(rs);
		    map.put(rs.getInt("DepartmentId"), dep);	// armazenando um novo departamento
		}
		
		Seller seller = instantiateSeller(rs, dep);		
		list.add(seller);
	    }
	    
	    return list;
	    
	} catch (SQLException ex) {
	    throw new DbException(ex.getMessage());
	} finally {
	    DB.closeStatement(st);
	    DB.closeResultSet(rs);
	}
    }

    /* Métodos auxiliares para evitar códigos verbosos dentro do findById */
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
	Department dep = new Department();
	dep.setId(rs.getInt("DepartmentId"));
	dep.setName(rs.getString("DepName"));
	return dep;
    }
    
    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
	Seller seller = new Seller();
	seller.setId(rs.getInt("Id"));
	seller.setName(rs.getString("Name")); 
	seller.setEmail(rs.getString("Email")); 
	seller.setBirthDate(rs.getDate("BirthDate")); 
	seller.setBaseSalary(rs.getDouble("BaseSalary"));
	seller.setDepartament(dep);
	return seller;
    }

}

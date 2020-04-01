package model.dao.impl;

import db.DbException;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
		Department dep = new Department(rs.getInt("DepartmentId"), 
						rs.getString("DepName"));
		Seller seller = new Seller(rs.getInt("Id"), 
					 rs.getString("Name"), 
					 rs.getString("Email"), 
					 rs.getDate("BirthDate"), 
					 rs.getDouble("BaseSalary"),
					 dep);
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
    public List<Seller> findAll() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

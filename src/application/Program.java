package application;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
		
	SellerDao sellerDao = DaoFactory.createSellerDao();	// injeção de dependência: a classe principal não conhece a implementação utilizada, isto é, utilizando-se da DaoFactory para criar um SellerDao, em vez do construtor SellerDao()
	
	System.out.println("===== TEST 1: seller - findById =====");
	Seller seller = sellerDao.findById(3);	
	System.out.println(seller);
	
	System.out.println("\n===== TEST 2: seller - findByDepartment =====");
	Department department = new Department(2, null);    // para a busca por id, não me interessa o nome;
	List<Seller> list = sellerDao.findByDepartment(department);
	for (Seller obj : list)
	    System.out.println(obj  );
	
    }
    
}

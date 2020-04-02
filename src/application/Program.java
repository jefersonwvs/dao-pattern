package application;

import java.util.Date;
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
	    System.out.println(obj);
	
	System.out.println("\n===== TEST 3: seller - findAll =====");
	list = sellerDao.findAll();
	for (Seller obj : list)
	    System.out.println(obj);
	
	System.out.println("\n===== TEST 4: seller - insert =====");
	seller = new Seller(null, "Robin Hood", "arrow@gmail.com", new Date(), 2505.00, department);
	sellerDao.insert(seller);
	System.out.println("Inserted! New id = " + seller.getId());
	
	System.out.println("\n===== TEST 5: seller - update =====");
	seller = sellerDao.findById(1);
	seller.setName("Bruce Wayne");
	seller.setEmail("batman@wayneenterprises.com");
	sellerDao.update(seller);
	System.out.println("Update completed!");
	
	System.out.println("\n===== TEST 6: seller - delete =====");
	sellerDao.deleteById(10);
	System.out.println("Delete completed!");
    }
    
}

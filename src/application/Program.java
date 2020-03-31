package application;

import java.util.Date;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
	
	Department obj = new Department(2, "Books");
	Seller obj2 = new Seller(2647, "Jeferson Willian", "jwvs@gmail.com", new Date(), 50000.00, obj);
	
	SellerDao sellerDao = DaoFactory.createSellerDao();	// injeção de dependência: a classe principal não conhece a implementação utilizada, isto é, utilizando-se da DaoFactory para criar um SellerDao, em vez do construtor SellerDao()
	
	System.out.println(obj);
	System.out.println(obj2);
	
    }
    
}

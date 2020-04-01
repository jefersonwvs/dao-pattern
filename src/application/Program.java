package application;

import java.util.Date;
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
	
    }
    
}

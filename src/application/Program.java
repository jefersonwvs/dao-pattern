package application;

import java.util.Date;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
	
	Department obj = new Department(2, "Books");
	Seller obj2 = new Seller(2647, "Jeferson Willian", "jwvs@gmail.com", new Date(), 50000.00, obj);
	
	System.out.println(obj);
	System.out.println(obj2);
	
    }
    
}

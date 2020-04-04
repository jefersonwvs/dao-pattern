package application;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
    
    public static void main(String[] args) {
	
	DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
	
	System.out.println("===== TEST 1: department - insert =====");
	Department dep = new Department(null, "Cars");
	departmentDao.insert(dep);
	System.out.println("Inserted! New id: " + dep.getId() + "\n");
	
	System.out.println("===== TEST 2: department - update =====");
	dep.setId(6);
	dep.setName("Music");
	departmentDao.update(dep);
	System.out.println("Update completed!\n");
	
	System.out.println("===== TEST 3: department - delete =====");
	departmentDao.deleteById(7);
	departmentDao.deleteById(8);
	System.out.println("Delete completed!\n");
	
	System.out.println("===== TEST 4: department - findById =====");
	dep = departmentDao.findById(3);
	System.out.println(dep + "\n");
	
	System.out.println("===== TEST 5: department - findAll =====");
	List<Department> list = departmentDao.findAll();
	for (Department obj : list)
	    System.out.println(obj);
	
	
    }

}

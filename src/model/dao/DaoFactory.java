package model.dao;

import java.util.List;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

public class DaoFactory {

    public static SellerDao createSellerDao() {
	return new SellerDaoJDBC();
    }
}

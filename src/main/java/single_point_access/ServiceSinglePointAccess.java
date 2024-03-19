package single_point_access;

import service.PolinomService;
import service.implementation.PolinomServiceImpl;

public class ServiceSinglePointAccess {
    private  static PolinomService polinomService;
    static{
        polinomService = new PolinomServiceImpl();
    }

    public static PolinomService getPolinomService() {
        return polinomService;
    }
}

package single_point_access;

import repository.PolinomRepository;
import repository.implementation.PolinomRepositoryImpl;

public class RepositorySinglePointAccess {
    private static PolinomRepository polinomRepository;

    static{
        polinomRepository = new PolinomRepositoryImpl();
    }

    public static PolinomRepository getPolinomService(){
        return polinomRepository;
    }
}

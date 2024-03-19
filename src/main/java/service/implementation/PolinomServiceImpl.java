package service.implementation;

import data.Polinom;
import org.apache.commons.lang3.tuple.Pair;
import repository.PolinomRepository;
import service.PolinomService;
import single_point_access.RepositorySinglePointAccess;

public class PolinomServiceImpl implements PolinomService {

    PolinomRepository polinomRepository = RepositorySinglePointAccess.getPolinomService();
    @Override
    public Polinom addPolinom(Polinom first, Polinom second) {
        return polinomRepository.addPolinom(first,second);
    }

    @Override
    public Polinom subPolinom(Polinom first, Polinom second) {
        return polinomRepository.subPolinom(first, second);
    }

    @Override
    public Polinom multiplyPolinom(Polinom first, Polinom second) {
        return polinomRepository.multiplyPolinom(first, second);
    }

    @Override
    public Pair<Polinom, Polinom> divPolinom(Polinom first, Polinom second) {
        return polinomRepository.divPolinom(first, second);
    }

    @Override
    public Polinom derivPolinom(Polinom first) {
        return polinomRepository.derivPolinom(first);
    }

    @Override
    public Polinom intPolinom(Polinom first) {
        return polinomRepository.intPolinom(first);
    }

    @Override
    public Polinom parsePolinom(String literal) {
        return polinomRepository.parsePolinom(literal);
    }
}

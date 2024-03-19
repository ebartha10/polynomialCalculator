package repository;

import data.Polinom;
import org.apache.commons.lang3.tuple.Pair;

public interface PolinomRepository {
    Polinom addPolinom(Polinom first, Polinom second);
    Polinom subPolinom(Polinom first, Polinom second);
    Polinom multiplyPolinom(Polinom first, Polinom second);
    Pair<Polinom, Polinom> divPolinom(Polinom first, Polinom second);
    Polinom derivPolinom(Polinom first);
    Polinom intPolinom(Polinom first);

    // CONVERTS A STRING POLYNOMIAL INTO POLINOM CLASS
    Polinom parsePolinom(String literal);


}

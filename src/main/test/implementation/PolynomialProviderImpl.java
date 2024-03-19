package implementation;

import data.Polinom;
import org.apache.commons.lang3.tuple.Pair;
import service.PolinomService;
import single_point_access.ServiceSinglePointAccess;

import java.util.ArrayList;
import java.util.List;

public class PolynomialProviderImpl implements PolynomialProvider{
    private final PolinomService polinomService = ServiceSinglePointAccess.getPolinomService();
    @Override
    public List<Polinom[]> provideOperationPolynomials(int opType) {
        List<Polinom[]> testCases = new ArrayList<>();
        switch (opType){
            case PolynomialProvider.OP_ADDITION:{
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("1"), new Polinom(new Integer[]{0}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{1}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{0,1}, new Double[]{1.0,2.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("-x"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x^2"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{1,2}, new Double[]{1.0,1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("2x^2 + 2x + 2"), polinomService.parsePolinom("2x^3 + 2x + 2"), new Polinom(new Integer[]{0,1,1,2,3}, new Double[]{4.0,4.0,4.0,2.0,2.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("-x-1"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0x"), polinomService.parsePolinom("0x^2"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("1"), polinomService.parsePolinom("-1"), new Polinom(new Integer[]{}, new Double[]{})});
                return testCases;
            }
            case PolynomialProvider.OP_SUBTRACTION:{
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("1"), new Polinom(new Integer[]{0}, new Double[]{-1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{1}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{0}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x^2"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{1,2}, new Double[]{-1.0,1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("2x^2 + 2x + 2"), polinomService.parsePolinom("2x^3 + 2x + 2"), new Polinom(new Integer[]{2,3}, new Double[]{2.0,-2.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("x-1"), new Polinom(new Integer[]{0}, new Double[]{2.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0x"), polinomService.parsePolinom("0x^2"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("1"), polinomService.parsePolinom("1"), new Polinom(new Integer[]{}, new Double[]{})});
                return testCases;
            }
            case PolynomialProvider.OP_MULTIPLICATION:{
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("1"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{1,2}, new Double[]{1.0,1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{2}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x^2"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{3}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("2x^2 + 2x + 2"), polinomService.parsePolinom("2x^3 + 2x + 2"), new Polinom(new Integer[]{0,1,2,3,4,5}, new Double[]{4.0,8.0,8.0,8.0,4.0,4.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("x-1"), new Polinom(new Integer[]{0,2}, new Double[]{-1.0,1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0x"), polinomService.parsePolinom("0x^2"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("1"), polinomService.parsePolinom("1"), new Polinom(new Integer[]{0}, new Double[]{1.0})});
                return testCases;
            }
            case PolynomialProvider.OP_DIVISION:{
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), polinomService.parsePolinom("1"), new Polinom(new Integer[]{}, new Double[]{}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{0}, new Double[]{1.0}), new Polinom(new Integer[]{0}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{0}, new Double[]{1.0}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x^2"), polinomService.parsePolinom("x"), new Polinom(new Integer[]{1}, new Double[]{1.0}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x^3-2x^2+6x-5"), polinomService.parsePolinom("x^2-1"), new Polinom(new Integer[]{0,1}, new Double[]{-2.0,1.0}), new Polinom(new Integer[]{0,1}, new Double[]{-7.0,7.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), polinomService.parsePolinom("x-1"), new Polinom(new Integer[]{0}, new Double[]{1.0}), new Polinom(new Integer[]{0}, new Double[]{2.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0x"), polinomService.parsePolinom("0x^2"), new Polinom(new Integer[]{}, new Double[]{}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("1"), polinomService.parsePolinom("1"), new Polinom(new Integer[]{0}, new Double[]{1.0}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("3x^2"), polinomService.parsePolinom("2x"), new Polinom(new Integer[]{1}, new Double[]{1.5}), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("3x^2"), polinomService.parsePolinom("2x^3"), new Polinom(new Integer[]{}, new Double[]{}), new Polinom(new Integer[]{}, new Double[]{})});
                return testCases;
            }
            case PolynomialProvider.OP_DERIVATIVE:{
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), new Polinom(new Integer[]{0}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("-x^2"), new Polinom(new Integer[]{1}, new Double[]{-2.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), new Polinom(new Integer[]{0}, new Double[]{1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("-x"), new Polinom(new Integer[]{0}, new Double[]{-1.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("2x^2"), new Polinom(new Integer[]{1}, new Double[]{4.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("2x^2 + 2x + 2"), new Polinom(new Integer[]{0,1}, new Double[]{2.0,4.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1+x+x+x"), new Polinom(new Integer[]{0}, new Double[]{4.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0x"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("420"), new Polinom(new Integer[]{}, new Double[]{})});
                return testCases;
            }
            case PolynomialProvider.OP_INTEGRATION:{
                testCases.add(new Polinom[]{polinomService.parsePolinom("0"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x"), new Polinom(new Integer[]{2}, new Double[]{0.5})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("-x^2"), new Polinom(new Integer[]{3}, new Double[]{-0.33333333333333333333333})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1"), new Polinom(new Integer[]{1,2}, new Double[]{1.0,0.5})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("-x"), new Polinom(new Integer[]{2}, new Double[]{-0.5})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("2x^2"), new Polinom(new Integer[]{3}, new Double[]{0.666666666666666666666666})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("2x^2 + 2x + 2"), new Polinom(new Integer[]{1,2,3}, new Double[]{2.0,1.0,0.66666666666666666666666})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("x+1+x+x+x"), new Polinom(new Integer[]{1,2}, new Double[]{1.0,2.0})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("0x"), new Polinom(new Integer[]{}, new Double[]{})});
                testCases.add(new Polinom[]{polinomService.parsePolinom("420"), new Polinom(new Integer[]{1}, new Double[]{420.0})});

                return testCases;
            }
            default: {
                return new ArrayList<>();
            }
        }
    }

}

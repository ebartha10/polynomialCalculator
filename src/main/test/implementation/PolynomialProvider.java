package implementation;

import data.Polinom;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface PolynomialProvider {

    public final int OP_ADDITION = 1;
    public final int OP_SUBTRACTION = 2;
    public final int OP_MULTIPLICATION = 3;
    public final int OP_DIVISION = 4;
    public final int OP_DERIVATIVE = 5;
    public final int OP_INTEGRATION = 6;

    List<Polinom[]> provideOperationPolynomials(int opType);
}

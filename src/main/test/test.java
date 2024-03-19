import implementation.PolynomialProvider;
import implementation.PolynomialProviderImpl;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import data.Polinom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.PolinomService;
import single_point_access.ServiceSinglePointAccess;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class test {
    public static PolinomService polinomService = ServiceSinglePointAccess.getPolinomService();
    public static PolynomialProvider polynomialProvider = new PolynomialProviderImpl();
    public static Stream<Arguments> getOperationParametersAddition(){
        List<Polinom[]> polinoms = polynomialProvider.provideOperationPolynomials(PolynomialProvider.OP_ADDITION);
        return polinoms.stream().map(vect -> Arguments.of(vect[0], vect[1], vect[2]));
    }
    public static Stream<Arguments> getOperationParametersSubtraction(){
        List<Polinom[]> polinoms = polynomialProvider.provideOperationPolynomials(PolynomialProvider.OP_SUBTRACTION);
        return polinoms.stream().map(vect -> Arguments.of(vect[0], vect[1], vect[2]));
    }
    public static Stream<Arguments> getOperationParametersMultiplication(){
        List<Polinom[]> polinoms = polynomialProvider.provideOperationPolynomials(PolynomialProvider.OP_MULTIPLICATION);
        return polinoms.stream().map(vect -> Arguments.of(vect[0], vect[1], vect[2]));
    }
    public static Stream<Arguments> getOperationParametersDivision(){
        List<Polinom[]> polinoms = polynomialProvider.provideOperationPolynomials(PolynomialProvider.OP_DIVISION);
        return polinoms.stream().map(vect -> Arguments.of(vect[0], vect[1], vect[2], vect[3]));
    }
    public static Stream<Arguments> getOperationParametersDerivative(){
        List<Polinom[]> polinoms = polynomialProvider.provideOperationPolynomials(PolynomialProvider.OP_DERIVATIVE);
        return polinoms.stream().map(vect -> Arguments.of(vect[0], vect[1]));
    }
    public static Stream<Arguments> getOperationParametersIntegral(){
        List<Polinom[]> polinoms = polynomialProvider.provideOperationPolynomials(PolynomialProvider.OP_INTEGRATION);
        return polinoms.stream().map(vect -> Arguments.of(vect[0], vect[1]));
    }
    @ParameterizedTest
    @MethodSource("getOperationParametersAddition")
    public void testMultipleAddition(Polinom p1, Polinom p2, Polinom expectedResult){
        Polinom result = polinomService.addPolinom(p1,p2);
        assertEquals(expectedResult, result);
    }
    @ParameterizedTest
    @MethodSource("getOperationParametersSubtraction")
    public void testMultipleSubtraction(Polinom p1, Polinom p2, Polinom expectedResult){
        assertEquals(expectedResult, polinomService.subPolinom(p1,p2));
    }
    @ParameterizedTest
    @MethodSource("getOperationParametersMultiplication")
    public void testMultipleMultiplication(Polinom p1, Polinom p2, Polinom expectedResult){
        assertEquals(expectedResult, polinomService.multiplyPolinom(p1,p2));
    }
    @ParameterizedTest
    @MethodSource("getOperationParametersDivision")
    public void testMultipleDivision(Polinom p1, Polinom p2, Polinom expectedQuotient, Polinom expectedRemainder){
        assertEquals(new MutablePair<>(expectedQuotient,expectedRemainder), polinomService.divPolinom(p1,p2));
    }

    @ParameterizedTest
    @MethodSource("getOperationParametersDerivative")
    public void testMultipleDerivative(Polinom p1, Polinom expectedResult){
        assertEquals(expectedResult, polinomService.derivPolinom(p1));
    }
    @ParameterizedTest
    @MethodSource("getOperationParametersIntegral")
    public void testMultipleIntegral(Polinom p1, Polinom expectedResult){
        assertEquals(expectedResult, polinomService.intPolinom(p1));
    }
    @Test
    public void testGenericAddition(){
        Polinom p1 = polinomService.parsePolinom("2x^2 + 2x");
        Polinom p2 = polinomService.parsePolinom("3x^2 + 4x");
        Polinom res = polinomService.parsePolinom("5x^2 + 6x");
        assertTrue(res.equals(polinomService.addPolinom(p1,p2)));
    }
    @Test
    public void testGenericSubtraction(){
        Polinom p1 = polinomService.parsePolinom("2x^2 + 2x");
        Polinom p2 = polinomService.parsePolinom("3x^2 + 4x");
        Polinom res = polinomService.parsePolinom("-x^2 - 2x");
        assertTrue(res.equals(polinomService.subPolinom(p1,p2)));
    }
    @Test
    public void testGenericMultiply(){
        Polinom p1 = polinomService.parsePolinom("2x^2 + 2");
        Polinom p2 = polinomService.parsePolinom("3x^2 + 4");
        Polinom res = polinomService.parsePolinom("6x^4 + 14x^2 + 8");
        assertTrue(res.equals(polinomService.multiplyPolinom(p1,p2)));
    }
    @Test
    public void testGenericDeriv(){
        Polinom p1 = polinomService.parsePolinom("2x^2 + 2x");
        Polinom res = polinomService.parsePolinom("4x + 2");
        assertTrue(res.equals(polinomService.derivPolinom(p1)));
    }
    @Test
    public void testGenericDivide(){
        Polinom p1 = polinomService.parsePolinom("x^3 -2x^2 + 6x - 5");
        Polinom p2 = polinomService.parsePolinom("x^2 - 1");
        Pair<Polinom, Polinom> p = polinomService.divPolinom(p1,p2);
        Polinom res1 = polinomService.parsePolinom("x - 2");
        Polinom res2 =polinomService.parsePolinom("7x - 7");
        assertEquals(res1.prettyPrint() + res2.prettyPrint(), p.getLeft().prettyPrint() + p.getRight().prettyPrint());

    }

    @Test
    public void testParseEmptyString() {
        assertThrows(RuntimeException.class, () -> polinomService.parsePolinom(""));
    }

    @Test
    public void testParseWhiteSpace() {
        Polinom p1 = polinomService.parsePolinom("                                 ");
        Polinom p2 = polinomService.parsePolinom("                           ");
        assertTrue(p1.equals(new Polinom()));
        assertTrue(p2.equals(new Polinom()));

    }

    @Test
    public void testParseGarbage() {
        Polinom p1 = polinomService.parsePolinom("asdkljasndlkjcnaslkdcndc");
        Polinom p2 = polinomService.parsePolinom("askldj csdcasdcnjascjnsadjns");
        assertTrue(p1.equals(new Polinom()));
        assertTrue(p2.equals(new Polinom()));
    }

    @Test
    public void testParseHugeNumber() {
        Polinom p1 = polinomService.parsePolinom("12312378132681328");
        Polinom p2 = polinomService.parsePolinom("1276132761326123112123123");
        assertTrue(p1.prettyPrint().equals("12312378132681328.00"));
        assertTrue(p2.getCoefficients().get(0).equals(Double.parseDouble("1276132761326123112123123")));
    }

    @Test
    public void testParseMultipleSigns() {
        Polinom p1 = polinomService.parsePolinom("------------1");
        Polinom p2 = polinomService.parsePolinom("+-+-+-+-+-+++++++ 11");
        assertTrue(p1.prettyPrint().equals("1.00"));
        assertTrue(p2.prettyPrint().equals("-11.00"));

    }

    @Test
    public void testParseSeparateMonomialsSum() {
        Polinom p1 = polinomService.parsePolinom("x+x+x+x+x+x+x+x+x+x+x");
        Polinom p2 = polinomService.parsePolinom("x^2+x^2+x^2+x^2+x^2+x^2+x^2+x^2+x^2+x^2");
        assertTrue(p1.prettyPrint().equals("11.00x"));
        assertTrue(p2.prettyPrint().equals("10.00x^2"));

    }

    @Test
    public void testParseCombinedCapitalization() {
        Polinom p1 = polinomService.parsePolinom("x+X+X+x+X+x+x+X+x+X+x");
        Polinom p2 = polinomService.parsePolinom("x^2+X^2+x^2+X^2+x^2+X^2+x^2+X^2+x^2+X^2");
        assertTrue(p1.prettyPrint().equals("11.00x"));
        assertTrue(p2.prettyPrint().equals("10.00x^2"));

    }

    @Test
    public void testParsePolynomialWithMultipleSpaces() {
        Polinom p1 = polinomService.parsePolinom("x                +x                 +x+x+        x+x+x+x+x+x+x");
        Polinom p2 = polinomService.parsePolinom("x                  ^                     2+x^               2+x^               2+x         ^2+x             ^           2+x^2+x^2+x^2+x^2+x^2");
        assertTrue(p1.prettyPrint().equals("11.00x"));
        assertTrue(p2.prettyPrint().equals("10.00x^2"));

    }
}

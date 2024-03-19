package repository.implementation;

import data.Polinom;
import repository.PolinomRepository;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.max;

public class PolinomRepositoryImpl implements PolinomRepository {

    /**
     *
     * @param first Polynomial object.
     * @param second Polynomial object.
     * @return Sum of objects.
     */
    @Override
    public Polinom addPolinom(Polinom first, Polinom second) {
        Map<Integer, Double> result = first.getCoefficients();
        int grade = max(first.getGrade(), second.getGrade());
        second.getCoefficients().forEach((k,v) -> result.put(k, v + result.getOrDefault(k, 0.0)));
        return new Polinom(result, grade);
    }

    /**
     *
     * @param first Polynomial object.
     * @param second Polynomial object.
     * @return Difference of objects.
     */
    @Override
    public Polinom subPolinom(Polinom first, Polinom second) {
        Map<Integer, Double> result = first.getCoefficients();
        int grade = max(first.getGrade(), second.getGrade());
        second.getCoefficients().forEach((k,v) -> result.put(k, result.getOrDefault(k, 0.0) - v));
        return new Polinom(result, grade);
    }

    /**
     *
     * @param first Polynomial object.
     * @param second Polynomial object.
     * @return Product of objects.
     */
    @Override
    public Polinom multiplyPolinom(Polinom first, Polinom second) {
        HashMap<Integer, Double> result = new HashMap<>();
        int grade = first.getGrade() + second.getGrade();
        synchronized (result) {
            first.getCoefficients().forEach((keyF, valueF) ->
                    second.getCoefficients().forEach((keyS, valueS) ->
                            result.merge(keyF + keyS,
                                    (valueF * valueS), (oldvalue, newvalue)-> oldvalue + newvalue)
                    )
            );
        }

        return  new Polinom(result, grade);
    }

    /**
     *
     * @param first Polynomial object.
     * @param second Polynomial object.
     * @return Pair of Quotient and Remainder.
     */
    @Override
    public Pair<Polinom, Polinom> divPolinom(Polinom first, Polinom second) {
        if(second.prettyPrint().equals("0") || first.getGrade() < second.getGrade())        // DIVISION BY 0 OR INVALID DIVISION.
            return new MutablePair<>(new Polinom(), new Polinom());

        int steps = first.getGrade() - second.getGrade();
        HashMap<Integer,Double> quotientMap = new HashMap<>();
        int quotientGrade = 0;

        while(steps >= 0){
            int currentGrade = first.getGrade() - second.getGrade();                        // FIND MAX MONOMIAL POWER
            if(currentGrade > quotientGrade)                                                // SAVE THE MAX GRADE
                quotientGrade = currentGrade;

            double currentCoefficient =  first.getCoefficients().getOrDefault(first.getGrade(), 0.0) /
                    second.getCoefficients().getOrDefault(second.getGrade(), 1.0);

            quotientMap.put(currentGrade, currentCoefficient);                              // COMPUTE COEFFICIENT OF CURRENT MONOMIAL

            Map<Integer, Double> polynomialToSubtract = new HashMap<>();
            second.getCoefficients().forEach(
                    (k,v)-> polynomialToSubtract.put(k + currentGrade, v * currentCoefficient)
            );
            Polinom polToSubtract = new Polinom(polynomialToSubtract, first.getGrade());

            first = subPolinom(first, polToSubtract);
            first.setGrade(first.getGrade() - 1);
            steps--;
        }
        return new MutablePair<>(new Polinom(quotientMap, quotientGrade), first);
    }

    /**
     *
     * @param first Polynomial object.
     * @return Derivative of given object.
     */
    @Override
    public Polinom derivPolinom(Polinom first) {
        HashMap<Integer, Double> result = new HashMap<>();
        int grade = first.getGrade() - 1;
        for (int i = 0; i <= grade; i++){
            result.put(i, first.getCoefficients().getOrDefault(i + 1, 0.0) * (i + 1));
        }

        return new Polinom(result, grade);
    }

    /**
     *
     * @param first Polynomial object.
     * @return Primitive of given object.
     */
    @Override
    public Polinom intPolinom(Polinom first) {
        HashMap<Integer, Double> result = new HashMap<>();
        int grade = first.getGrade() + 1;
        for (int i = grade; i > 0; i--){
            result.put(i, first.getCoefficients().getOrDefault(i - 1, 0.0)/ (float) i);

        }
        return new Polinom(result, grade);
    }

    /** Parses a number from a string into a Double.
     *
     * @param givenCoefficient string containing the number
     * @return number extracted from the string
     */
    private Double parseCoefficient(String givenCoefficient){
        long numberOfMinus = givenCoefficient.chars().filter(ch -> ch == '-').count();               // COUNT THE NUMBER OF MINUS FROM COEFFICIENT
        givenCoefficient = givenCoefficient.replace("-", "");
        givenCoefficient = givenCoefficient.replace("+", "");
        double coefficient;

        if(givenCoefficient.isEmpty()){
            coefficient = 1.0;
        }
        else{
            coefficient = Double.parseDouble(givenCoefficient);
        }

        if(numberOfMinus % 2 == 1){
            coefficient = -coefficient;
        }
        return coefficient;
    }

    /**
     * Converts a string polynomial into a Polynomial object.
     * @param literal String containing the Polynomial.
     * @return Polinom object.
     */
    @Override
    public Polinom parsePolinom(String literal) {
        if(literal.isEmpty() && !literal.matches("[\\sx\\d+\\-\\^]+")){
            throw new RuntimeException("ILLEGAL POLYNOMIAL");
        }
        literal = literal.toLowerCase();
        literal = literal.replace(" ", "");                                            // REMOVE WHITE SPACES
        Map<Integer, Double> coefficientMap = new HashMap<>();
        int maxGrade = 0;
        // REGEX MATCHES ALL THE TYPES OF MONOMIALS: aX^b, aX, a
        // [?+-]*\d*x\^[+-]*\d  -> MATCH    ES aX^b
        // [?+-]*\d*x           -> MATCHES aX
        // [?+-]*\d             -> MATCHES ANY SINGULAR DIGITS
        final String regex = "[?+-]*\\d*x\\^[+-]*\\d|[?+-]*\\d*x|[?+-]*\\d*";                            // CREATES REGEX
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);                              // COMPILES REGEX TO PATTERN
        final Matcher matcher = pattern.matcher(literal);                                               // SPLITS THE STRING INTO MONOMIALS USING REGEX

        while (matcher.find()){
            String currentMonomial = matcher.group(0);                                                  // SAVE THE CURRENT MONOMIAL
            if(currentMonomial.isEmpty()){
                break;
            }
            double coefficient = 0.0;
            int power = 0;
            if(currentMonomial.contains("^")){                                                          // THE MONOMIAL IS OF POWER GREATER THAN 1
                String[] elements = currentMonomial.split("x\\^");                                // SPLIT THE COEFFICIENTS
                coefficient = parseCoefficient(elements[0]);
                power = parseCoefficient(elements[1]).intValue();
            }
            else if(currentMonomial.contains("x")){                                                     // IF MONOMIAL CONTAINS x -> GRADE = 1
                currentMonomial = currentMonomial.replace("x", "");
                coefficient = parseCoefficient(currentMonomial);
                power = 1;
            }
            else{                                                                                       // GRADE 0 MONOMIAL
                coefficient = parseCoefficient(currentMonomial);
                power = 0;
            }

            if(!coefficientMap.containsKey(power)) {                                                    // SAVE THE MONOMIAL
                coefficientMap.put(power,coefficient);
            }
            else{
                coefficientMap.merge(power, coefficient, (oldValue, newValue) -> oldValue + newValue);
            }
            if(power > maxGrade && coefficient != 0.0){
                maxGrade = power;
            }
        }
        return new Polinom(coefficientMap, maxGrade);
    }

}

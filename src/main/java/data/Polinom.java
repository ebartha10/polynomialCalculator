package data;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Polinom {
    private Map<Integer, Double> coefficients;
    private int grade;

    public Polinom()     {
        this.coefficients = new HashMap<>();
        this.grade = 0;
    }

    public Polinom(Map<Integer, Double> coefficients, int grade) {
        this.coefficients = new HashMap<>();
            for (int key : coefficients.keySet()) {
                if (coefficients.get(key) != 0.0) {
                    this.coefficients.put(key, coefficients.get(key));
                }
        }
        this.grade = grade;
    }
    public Polinom(Integer[] power, Double[] coef){
        if(power.length != coef.length){
            this.coefficients = new HashMap<>();
            this.grade = 0;
        }
        else{
            this.grade = 0;
            this.coefficients = new HashMap<>();

            for (int i = 0; i < power.length; i ++){
                this.coefficients.put(power[i], coef[i]);
                if(power[i] > this.grade && coef[i] != 0.0){
                    this.grade = power[i];
                }
            }
        }
    }

    public String prettyPrint() {
        String res = "";
        for (int i = this.grade; i >= 0; i --){
            if(!coefficients.containsKey(i))
                continue;
            if(i == 0){
                if(coefficients.get(i) != 0)
                    res = res + (coefficients.get(i) > 0 ? " + " : " ") + String.format("%.2f",(coefficients.get(i)));
            }
            else if(Math.abs(i) == 1){
                if(coefficients.get(i) != 0)
                    res = res + (coefficients.get(i) > 0 ? " + " : " ") + (coefficients.get(i) == 1 ? "" : String.format("%.2f",(coefficients.get(i)))) + "x";
            }
            else{
                if(coefficients.get(i) != 0)
                    res = res + (coefficients.get(i) > 0 ? " + " : " ") + (coefficients.get(i) == 1 ? "" : String.format("%.2f",(coefficients.get(i)))) + "x^" + i;

            }
        }
        res = res.replaceFirst("^\\s*\\+\\s", "");
        res = res.replace(" ","");
        if(res.equals(""))
            res = "0";
        return res;
    }

    public void setCoefficients(HashMap<Integer, Double> coefficients) {
        this.coefficients = coefficients;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Map<Integer, Double> getCoefficients() {
        return coefficients;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object obj) {
        if(coefficients.size() != ((Polinom)obj).getCoefficients().size()){
            return false;
        }
        else{
            for (Map.Entry<Integer, Double> pair : coefficients.entrySet()) {
                if(!((Polinom)obj).getCoefficients().entrySet().contains(pair)){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Polinom{" +
                "coefficients=" + coefficients +
                ", grade=" + grade +
                '}';
    }
}

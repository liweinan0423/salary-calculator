public class Main {
    private Factors factors;
    private double annualGross;
    private double months;

    public Main(int annualGross, int months, Factors factors) {
        this.annualGross = annualGross;
        this.months = months;
        this.factors = factors;
    }

    public static void main(String[] args) {
        System.out.printf("%10s%10s%10s%10s%10s%10s%10s%10s%10s\n", "月", "年薪", "税前", "税后", "个税", "奖金", "奖金税", "净奖金", "总税");
        for (int i = 12; i <= 20; i++) {
            run(200000, i);
        }
    }

    private static void run(int annualGross, int months) {
        Main main = new Main(annualGross, months, new Factors(4707, 0.6, 3.0, 0.02, 0.08, 0.12));
        System.out.printf("%10d", months);
        System.out.printf("%10.2f\t", main.annualGross);
        System.out.printf("%10.2f\t", main.monthlyGross());
        System.out.printf("%10.2f\t", main.net());
        System.out.printf("%10.2f\t", main.tax());
        System.out.printf("%10.2f\t", main.bonus());
        System.out.printf("%10.2f\t", main.bonusTax());
        System.out.printf("%10.2f\t", main.netBonus());
        System.out.printf("%10.2f\t", main.tax() * 12 + main.bonusTax());
        System.out.println();
    }

    private double netBonus() {
        return bonus() - bonusTax();
    }

    private double bonusTax() {
        double v = bonus() / 12;
        if (v <= 1500) {
            return bonus() * 0.03;
        } else if (v > 1500 && v <= 4500) {
            return bonus() * 0.10 - 105;
        } else if (v > 4500 && v <= 9000) {
            return bonus() * 0.20 - 555;
        } else if (v > 9000 && v <= 35000) {
            return bonus() * 0.25 - 1005;
        } else if (v > 35000 && v <= 55000) {
            return bonus() * 0.3 - 2755;
        } else if (v > 55000 && v <= 80000) {
            return bonus() * 0.35 - 5505;
        } else {
            return bonus() * 0.4 - 13505;
        }
    }

    private double monthlyGross() {
        return annualGross / months;
    }

    private double bonus() {
        return annualGross - (monthlyGross() * 12);
    }

    private double net() {
        return monthlyGross() - socialSum() - tax();
    }

    private double beforeTax() {
        return monthlyGross() - socialSum();
    }

    private double socialSum() {
        return retireSum() + medicalSum() + housingSum();
    }

    private double housingSum() {
        return socialInsuranceBase() * housingRate();
    }

    private double medicalSum() {
        return socialInsuranceBase() * medicalRate();
    }

    private double retireSum() {
        return socialInsuranceBase() * retireRate();
    }

    public double socialInsuranceBase() {
        if (monthlyGross() <= average() * bottomRate()) {
            return average() * bottomRate();
        } else {
            if (monthlyGross() > average() * bottomRate() && monthlyGross() <= average() * upperRate()) {
                return monthlyGross();
            } else if (monthlyGross() > average() * upperRate()) {
                return average() * upperRate();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public double tax() {
        double v = beforeTax() - 3500;
        if (v <= 0) {
            return 0;
        } else if (v < 1500) {
            return v * 0.03;
        } else if (v > 1500 && v <= 4500) {
            return v * 0.1 - 105;
        } else if (v > 4500 && v <= 9000) {
            return v * 0.2 - 555;
        } else if (v > 9000 && v <= 35000) {
            return v * 0.25 - 1005;
        } else if (v > 35000 && v <= 55000) {
            return v * 0.3 - 2755;
        } else if (v > 55000 && v <= 80000) {
            return v * 0.35 - 5505;
        } else {
            return v * 0.4 - 13505;
        }
    }

    public double average() {
        return factors.getAverage();
    }

    public double bottomRate() {
        return factors.getBottomRate();
    }

    public double upperRate() {
        return factors.getUpperRate();
    }

    public double medicalRate() {
        return factors.getMedicalRate();
    }

    public double retireRate() {
        return factors.getRetireRate();
    }

    public double housingRate() {
        return factors.getHousingRate();
    }

}

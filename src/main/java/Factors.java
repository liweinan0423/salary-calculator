public class Factors {
    private double average = 4707;
    private double bottomRate = 0.6;
    private double upperRate = 3.0;
    private double medicalRate = 0.02;
    private double retireRate = 0.08;
    private double housingRate = 0.12;

    public Factors(double average, double bottomRate, double upperRate, double medicalRate, double retireRate, double housingRate) {
        this.average = average;
        this.bottomRate = bottomRate;
        this.upperRate = upperRate;
        this.medicalRate = medicalRate;
        this.retireRate = retireRate;
        this.housingRate = housingRate;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getBottomRate() {
        return bottomRate;
    }

    public double getUpperRate() {
        return upperRate;
    }

    public double getMedicalRate() {
        return medicalRate;
    }

    public void setMedicalRate(double medicalRate) {
        this.medicalRate = medicalRate;
    }

    public double getRetireRate() {
        return retireRate;
    }

    public void setRetireRate(double retireRate) {
        this.retireRate = retireRate;
    }

    public double getHousingRate() {
        return housingRate;
    }

    public void setHousingRate(double housingRate) {
        this.housingRate = housingRate;
    }
}

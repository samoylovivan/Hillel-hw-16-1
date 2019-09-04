package ivan.samoylov;

public class ExchangeRate {
    private String currency;
    private double saleRateNB;

    public String getCurrency() {
        return currency;
    }

    public double getSaleRateNB() {
        return saleRateNB;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                '}';
    }
}

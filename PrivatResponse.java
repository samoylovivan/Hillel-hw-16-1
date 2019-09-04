package ivan.samoylov;

import java.util.List;

public class PrivatResponse {
    private String date;
    private List<ExchangeRate> exchangeRate;

    public List<ExchangeRate> getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String toString() {
        return "PrivatResponse{" +
                "date='" + date + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}

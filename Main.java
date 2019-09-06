package ivan.samoylov;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Scanner;

public class Main {
    private static final String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";

    public static void main(String[] args) {
        String date;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter date in dd.MM.yyyy format: ");
        date = in.next();
        in.close();

        String json = HttpUtil.sendRequest(URL + date, null, null);
        Gson gson = new Gson();
        PrivatResponse response = gson.fromJson(json, PrivatResponse.class);

        try {
            for (ExchangeRate rate : response.getExchangeRate()) {
                if ("USD".equals(rate.getCurrency())) {
                    System.out.println("Rate: " + rate.getSaleRateNB());
                    break;
                }
            }
        }
        catch (NullPointerException e){
            System.out.println("Date is not valid.");
        }
        catch (JsonSyntaxException e){
            System.out.println("Server responded with an error.");
        }
    }
}

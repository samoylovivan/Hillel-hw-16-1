package ivan.samoylov;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter date in dd.MM.yyyy format: ");
        String date = in.next();
        in.close();
        
        if (!isValid(date)){
            System.out.println("Date is not valid. Exit.");
            return;
        }

        String json = HttpUtil.sendRequest(URL + date, null, null);
        Gson gson = new Gson();
        PrivatResponse response = gson.fromJson(json, PrivatResponse.class);

        for (ExchangeRate rate : response.getExchangeRate()) {
            if ("USD".equals(rate.getCurrency())) {
                System.out.println("Rate: " + rate.getSaleRateNB());
                break;
            }
        }
    }

    private static boolean isValid(String date){
        final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return DATE_FORMAT.format(DATE_FORMAT.parse(date)).equals(date);
        }catch (ParseException ex){
            return false;
        }
    }
}

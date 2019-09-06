package ivan.samoylov;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import static java.time.Period.*;
import static java.time.Period.ofYears;
import static java.time.temporal.ChronoUnit.*;

public class Main {
    private static final String URL = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        String strDate;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter date in dd.MM.yyyy format: ");
        strDate = in.next();
        in.close();

        try {
            LocalDate date = LocalDate.parse(strDate, DATE_FORMAT);
            LocalDate today = LocalDate.now();
            if(today.isAfter(date) || today.isBefore(date.minusYears(4))) {
                System.out.print("The date should be no later than 4 years");
                System.out.println(" and should not be more than the current day. Try again.");
                return;
            }
            String json = HttpUtil.sendRequest(URL + strDate, null, null);
            Gson gson = new Gson();
            PrivatResponse response = gson.fromJson(json, PrivatResponse.class);
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


package com.oracle.exchangerate.models;

public class ExchangeRateApiResponse {
    private String baseCode;
    private String targetCode;
    private double exchangeRate;
    private double amount;
    private double conversionResult;

    public ExchangeRateApiResponse(String baseCode, String targetCode, double exchangeRate, double amount, double conversionResult) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.exchangeRate = exchangeRate;
        this.amount = amount;
        this.conversionResult = conversionResult;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public double getAmount() {
        return amount;
    }

    public double getConversionResult() {
        return conversionResult;
    }

    public String getFormattedConversionResult() {
        return String.format("%.2f %s", conversionResult, targetCode);
    }
}

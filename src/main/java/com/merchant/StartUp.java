package com.merchant;


import com.merchant.service.MerchantService;
import com.merchant.service.impl.MerchantServiceImpl;
import com.merchant.util.RomanToDecimal;

public class StartUp {
    public static void main(String[] args) throws Exception {
        MerchantService merchantService = new MerchantServiceImpl();
        merchantService.handle("input");
    }
}

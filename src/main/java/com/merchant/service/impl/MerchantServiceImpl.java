package com.merchant.service.impl;

import com.merchant.data.MappingData;
import com.merchant.service.MerchantService;
import com.merchant.service.ProcessorService;
import com.merchant.service.impl.processor.InputProcessor;
import com.merchant.service.impl.processor.OutPutProcess;

public class MerchantServiceImpl implements MerchantService {
    @Override
    public void handle(String inputFile) throws Exception {
        ProcessorService<String> inputProcess = new InputProcessor();
        ProcessorService outPutProcess = new OutPutProcess();
        inputProcess.setNextChange(outPutProcess);
        inputProcess.handle(inputFile);


    }
}

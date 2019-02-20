package unit;

import com.merchant.data.MappingData;
import com.merchant.service.ProcessorService;
import com.merchant.service.impl.processor.InputProcessor;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class InPutProcessorTest {

    @Test
    public void testAssignGrobIsI() throws Exception {
        ProcessorService<String> inputProcess = new InputProcessor();

        Method method = InputProcessor.class.getDeclaredMethod("processLine", MappingData.class, String.class);
        method.setAccessible(true);
        MappingData mappingData = new MappingData();
        method.invoke(inputProcess, mappingData, "glob is I");


        Assert.assertEquals("I", mappingData.getConstantAssign().get("glob"));
        Assert.assertEquals(new Float(1F), mappingData.getTokenIntegerValue().get("glob"));
    }
}

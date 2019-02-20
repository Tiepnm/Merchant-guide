package intergration;

import com.merchant.service.MerchantService;
import com.merchant.service.impl.MerchantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ProgramTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testHowMuchQuestionIsSuccess() throws Exception {
        MerchantService merchantService = new MerchantServiceImpl();
        merchantService.handle("how_much_success");
        Assert.assertEquals(("how much is pish tegj glob glob ? pish tegj glob glob is 42.0")
                , outContent.toString().trim());
    }

    @Test
    public void testHowManyQuestionIsSuccess() throws Exception {
        MerchantService merchantService = new MerchantServiceImpl();
        merchantService.handle("how_many_success");
        Assert.assertEquals(("how many Credits is pish tegj glob Iron ? pish tegj glob Iron is 8015.5 Credits")
                , outContent.toString().trim());
    }

    @Test
    public void testOtherQuestionIsSuccess() throws Exception {
        MerchantService merchantService = new MerchantServiceImpl();
        merchantService.handle("other_question_success");
        Assert.assertEquals(("Does pish tegj glob glob Iron has more Credits than glob glob Gold ? pish tegj glob glob smaller than glob glob")
                , outContent.toString().trim());
    }

    @Test
    public void testNoIdea() throws Exception {
        MerchantService merchantService = new MerchantServiceImpl();
        merchantService.handle("no_idea_success");
        Assert.assertEquals(("how much wood could a woodchuck chuck if a woodchuck could chuck wood ? : I have no idea what you are talking about")
                , errContent.toString().trim());
    }
}

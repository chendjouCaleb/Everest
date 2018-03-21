import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.router.variableResolver.RequestVariableResolver;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void convert(){
        RequestVariableResolver requestVariableResolver = new RequestVariableResolver();
        String a = "#10";
        System.out.println(a.substring(0));
    }
}

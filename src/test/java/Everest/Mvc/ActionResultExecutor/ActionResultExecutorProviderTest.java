package Everest.Mvc.ActionResultExecutor;

import Everest.Core.Exception.InvalidOperationException;
import Everest.Http.HttpContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionResultExecutorProviderTest {

    private ActionResultExecutorProvider executorProvider = new ActionResultExecutorProvider();

    @BeforeEach
    void setUp() {
    }

    @Test
    void addExecutor() {
        Fake1ResultExecutor executor1 = new Fake1ResultExecutor();
        Fake2ResultExecutor executor2 = new Fake2ResultExecutor();
        executorProvider.addExecutor(executor1);
        executorProvider.addExecutor(executor2);

        assertEquals(2, executorProvider.getExecutors().size());

    }

    @Test
    void addExecutor_withoutGenerics(){
        Fake3ResultExecutor executor = new Fake3ResultExecutor();
        assertThrows(InvalidOperationException.class, () -> executorProvider.addExecutor(executor));
    }

    @Test
    void getExecutor() {
        Fake1ResultExecutor executor1 = new Fake1ResultExecutor();
        Fake2ResultExecutor executor2 = new Fake2ResultExecutor();
        executorProvider.addExecutor(executor1);
        executorProvider.addExecutor(executor2);

        assertEquals(executor1, executorProvider.getExecutor(FakeResult1.class));
        assertEquals(executor2, executorProvider.getExecutor(FakeResult2.class));
    }
}

class FakeResult1{ }
class FakeResult2{ }
class Fake1ResultExecutor implements IResultExecutor<FakeResult1>{
    public void execute(HttpContext httpContext, FakeResult1 result) {  }
}

class Fake2ResultExecutor implements IResultExecutor<FakeResult2>{
    public void execute(HttpContext httpContext, FakeResult2 result) {  }
}
class Fake3ResultExecutor implements IResultExecutor{
    public void execute(HttpContext httpContext, Object result) {  }
}
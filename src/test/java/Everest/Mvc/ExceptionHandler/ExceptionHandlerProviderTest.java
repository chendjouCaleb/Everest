package Everest.Mvc.ExceptionHandler;

import Everest.Core.Exception.InvalidOperationException;
import Everest.Core.Exception.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerProviderTest {
    private ExceptionHandlerProvider provider = new ExceptionHandlerProvider();
    @BeforeEach
    void setUp() {
    }

    @Test
    void addExceptionHandler() {
        provider.addExceptionHandler(new NoSuchElementExceptionHandler());
        provider.addExceptionHandler(new NullExceptionHandler());

        assertEquals(3, provider.getHandlers().size());
    }

    @Test
    void addExceptionHandler_with_already_present_ExceptionClass(){
        provider.addExceptionHandler(new NoSuchElementExceptionHandler());
        assertThrows(InvalidOperationException.class, () -> provider.addExceptionHandler(new NoSuchElementExceptionHandlerBis()));
    }

    @Test
    void getExceptionHandler() {
        NoSuchElementExceptionHandler noSuchElementExceptionHandler = new NoSuchElementExceptionHandler();
        NullExceptionHandler nullExceptionHandler = new NullExceptionHandler();
        provider.addExceptionHandler(noSuchElementExceptionHandler);
        provider.addExceptionHandler(nullExceptionHandler);

        assertEquals(noSuchElementExceptionHandler, provider.getExceptionHandler(NoSuchElementException.class));
        assertEquals(nullExceptionHandler, provider.getExceptionHandler(NullPointerException.class));
        assertEquals(nullExceptionHandler, provider.getExceptionHandler(NullArgumentException.class));
    }

    @Test
    void getExceptionNoExisting_Handler(){
        assertThrows(NoSuchElementException.class, () -> provider.getExceptionHandler(InvalidOperationException.class));
    }



    private class NoSuchElementExceptionHandler implements IExceptionHandler{
        @Override
        public Collection<Class<? extends Throwable>> getExceptionTypes() {
            return Collections.singletonList(NoSuchElementException.class);
        }
        public void handleException(ExceptionContext context) { }
    }

    private class NoSuchElementExceptionHandlerBis implements IExceptionHandler{
        @Override
        public Collection<Class<? extends Throwable>> getExceptionTypes() {
            return Collections.singletonList(NoSuchElementException.class);
        }

        public void handleException(ExceptionContext context) { }
    }

    private class NullExceptionHandler implements IExceptionHandler{
        public Collection<Class<? extends Throwable>> getExceptionTypes() {
            return Arrays.asList(NullPointerException.class, NullArgumentException.class);
        }
        public void handleException(ExceptionContext context) { }
    }
}
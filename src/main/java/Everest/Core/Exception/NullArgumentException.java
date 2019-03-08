package Everest.Core.Exception;

/**
 * @author Chendjou deGrace
 * The Exception to throw when a param of a method or class-constructor is null
 */
public class NullArgumentException extends RuntimeException {

    /**
     * The constructor to  throw exception with a message that indicate the name of null argument
     * @param name The name of null argument
     */
    public NullArgumentException(String name){
        super("The argument "+name+ " must be not null");
    }

    /**
     * The default constructor that indicate the nullity of a argument
     */
    public NullArgumentException(){
        super("The method or constructor have a not authorized null argument");
    }
}

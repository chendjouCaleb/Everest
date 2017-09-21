package filter;

public class Target {
    public void execute(String request, String response){
        System.out.println("FINAL REQUEST: " + request);
    }
}

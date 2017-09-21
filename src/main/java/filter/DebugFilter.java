package filter;

public class DebugFilter implements Filter {
    @Override
    public void execute(String request, String response, FilterChain filterChain) {
        System.out.println("Debug log: " + request);
        filterChain.doNext(request, response);
    }
}

package filter;

public class AuthenticationFilter implements Filter {
    @Override
    public void execute(String request,String response, FilterChain filterChain) {
        request = request + "2";
        System.out.println("Authentification\n Ihave edit request: " + request);
        filterChain.doNext(request, response);
    }
}

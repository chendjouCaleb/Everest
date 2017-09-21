package filter;
public interface Filter {
    public void execute(String request,String Response, FilterChain filterChain);
}

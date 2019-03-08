package Everest.Http;

public abstract class ConnectionInfo {
    public abstract String id();
    public abstract String remoteIpAdress();
    public abstract String localIPAddress();
    public abstract int remotePort();
    public abstract int localPort();
}

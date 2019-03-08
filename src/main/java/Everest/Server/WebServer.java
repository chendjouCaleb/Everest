package Everest.Server;

import org.everest.mvc.infrastructure.MvcStartup;

public abstract class WebServer {
    /**
     * Java program arguments
     */
    protected String[] programArgs;

    /**
     * The Server port
     * The default port is 8080
     */
    protected int port = 8080;

    /**
     * Server context directory
     * The default directory is /web
     */
    protected String contextDirectory = "web";

    /**
     * The server http url hostPath
     * default is '/'
     */
    protected String hostPath = "/";

    /**
     * The directory of compiled class
     * The default is "target/classes" for maven
     */
    protected String targetClassDirectory = "target/classes";

    /**
     * The application configuration classes
     */
    protected Class<? extends MvcStartup> mvcStartup;

    /**
     * To create the web server
     */
    public abstract void build();

    /**
     * Do that the server can receive a http request
     */
    public abstract void listen();


    /**
     * Stop the server. The server cannot receive http request
     */
    public abstract void stop();

    /**
     * Check if server has started
     * @return True if server has started
     */
    public abstract boolean hasStarted();

    /**
     * Get the program launch arguments
     * @return The program launch arguments
     */
    public String[] getProgramArgs() {
        return programArgs;
    }

    /**
     * Set the program launch arguments
     */
    public void setProgramArgs(String[] programArgs) {
        this.programArgs = programArgs;
    }

    public int getPort() {
        return port;
    }

    /**
     * Set the application http port
     * @param port The application Http port
     */
    public void setPort(int port) {
        this.port = port;
    }

    public String getContextDirectory() {
        return contextDirectory;
    }

    /**
     * Set the application context path directory
     * @param contextDirectory The application context path directory
     */
    public void setContextDirectory(String contextDirectory) {
        this.contextDirectory = contextDirectory;
    }


    public Class<? extends MvcStartup> getMvcStartup() {
        return mvcStartup;
    }

    /**
     * Set the applicationConfiguration class
     */
    public void setMvcStartup(Class<? extends MvcStartup> mvcStartup) {
        this.mvcStartup = mvcStartup;
    }

    public String getHostPath() {
        return hostPath;
    }

    public void setHostPath(String hostPath) {
        this.hostPath = hostPath;
    }

    public String getTargetClassDirectory() {
        return targetClassDirectory;
    }

    public void setTargetClassDirectory(String targetClassDirectory) {
        this.targetClassDirectory = targetClassDirectory;
    }
}

package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private String ip;
    private int port;
    private Connection state;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public String getCurrentState() {
        return this.getState().getName();
    }

    public Connection getState() {
        return this.state;
    }

    public void setState(Connection state) {
        this.state = state;
    }

    public void connect() {
        this.getState().connect();
    }

    public void disconnect() {
        this.getState().disconnect();
    }

    public void write(String data) {
        this.getState().write(data);
    }
}
// END

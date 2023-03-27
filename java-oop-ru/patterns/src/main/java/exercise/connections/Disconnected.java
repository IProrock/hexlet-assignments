package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcpConnection;
    private final String name = "disconnected";

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }


    @Override
    public void connect() {
        System.out.println("Now connection established");
        this.tcpConnection.setState(new Connected(this.tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error - connection already closed.");

    }

    @Override
    public void write(String data) {
        System.out.println("Error - connection closed, impossible to write.");

    }

    public String getName() {
        return this.name;
    }
}
// END

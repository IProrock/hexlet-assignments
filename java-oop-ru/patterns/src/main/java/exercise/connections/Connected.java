package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private TcpConnection tcpConnection;
    private String name;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
        this.name = "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error - connection already established");
    }

    @Override
    public void disconnect() {
        System.out.println("Now connection is closed.");
        this.tcpConnection.setState(new Disconnected(this.tcpConnection));
    }

    @Override
    public void write(String data) {
        System.out.println("Data added to buffer");

    }

    @Override
    public String getName() {
        return this.name;
    }
}
// END

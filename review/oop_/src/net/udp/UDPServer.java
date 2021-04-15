package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/** UDP 网络编程服务端
 * @author hyc
 * @date 2021/4/15
 */
public class UDPServer {
    public static void main(String[] args) {

        DatagramPacket packet = null;
        try {
            DatagramSocket server = new DatagramSocket(9968);
            byte[] buf = new byte[1024];
            packet = new DatagramPacket(buf,0,buf.length);

            server.receive(packet);

            System.out.println(new String(packet.getData(),0,packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

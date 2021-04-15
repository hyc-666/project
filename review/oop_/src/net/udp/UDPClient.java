package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/** UDP 网络编程
 * @author hyc
 * @date 2021/4/15
 */
public class UDPClient {
    public static void main(String[] args) {
        //UDP 不需要建立连接,也需要一个socket,只不过是DatagramSocket;指定端口号即可
        try {
            DatagramSocket client = new DatagramSocket();
            //DatagramPacket提供封装数据的数据包
            String str = "UDP数据报";
            byte[] data = str.getBytes();
            InetAddress address = InetAddress.getByName("192.168.197.1");
            DatagramPacket packet = new DatagramPacket(data,0,data.length,address,9968);
            client.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("数据报已发送!");

    }
}

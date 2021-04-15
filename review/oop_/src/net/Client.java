package net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author hyc
 * @date 2021/4/15
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {

            InetAddress server = InetAddress.getLocalHost();
            System.out.println(server.getHostAddress());
            //客户端创建socket连接,指明需要建立连接的ip和端口号
            InetAddress client = InetAddress.getByName(server.getHostAddress());
            socket = new Socket(client,9920);
            outputStream = socket.getOutputStream();//向外发送数据,获取输出流
            outputStream.write("这是客户端的消息".getBytes());//将要发送的数据写入输出流
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

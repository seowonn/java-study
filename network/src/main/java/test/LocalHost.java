package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {

		try {
			// 로컬 머신의 네트워크 주소를 반환
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			// 호스트 이름 및 IP 주소를 출력
			String hostName = inetAddress.getHostName();
			String hostIPAddress = inetAddress.getHostAddress();
			
			System.out.println(hostName);
			System.out.println(hostIPAddress);
			
			// IP 주소를 바이트 배열로 반환 
			byte[] IPAddresses = inetAddress.getAddress();
			for (byte IPAddress : IPAddresses) {
				System.out.println(IPAddress & 0x000000ff);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}

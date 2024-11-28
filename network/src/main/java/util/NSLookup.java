package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			
			String domain = null;

			while (!"exit".equals(domain)) {
				domain = scanner.nextLine();
				try {
					InetAddress[] inetAddresses = InetAddress.getAllByName(domain);

					for (InetAddress inetAddress : inetAddresses) {
						System.out.println(inetAddress.getHostAddress());
					}
				} catch (UnknownHostException e) {

					e.printStackTrace();
				}
			}
		}

	}

}

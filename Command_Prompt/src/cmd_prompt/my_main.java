package cmd_prompt;

import java.io.PrintWriter;

public class my_main {
	public static void main(String[] args) {
		String[] command =
			{
					"cmd",
			};
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);

			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("cd D:\\Automation\\Projects\\test");
			stdin.println("docker-compose -f docker-compose.yaml up");
			stdin.println("docker-compose -f docker-compose.yaml down");


			stdin.close();
			p.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}	

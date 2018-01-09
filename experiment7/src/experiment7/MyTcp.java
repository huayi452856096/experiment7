package experiment7;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class MyTcp extends JFrame{ // ������MyTcp
	private BufferedReader reader; // ����BufferedReader����
	private ServerSocket server; // ����ServerSocket����
	private Socket socket; // ����Socket����socket
	
	private PrintWriter writer; // ����PrintWriter�����
	private JTextArea ta = new JTextArea(); // ����JtextArea����
	private JTextField tf = new JTextField(); // ����JtextField����
	Container cc; // ����Container����
	public MyTcp(String title) { // ���췽��
		super(title); // ���ø���Ĺ��췽��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc = this.getContentPane(); // ʵ��������

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf, "South"); // ���ı�����ڴ�����²�
		tf.addActionListener(new ActionListener() {
			// ���¼�
			public void actionPerformed(ActionEvent e) {
				// ���ı�������Ϣд����
				writer.println(tf.getText());
				// ���ı�������Ϣ��ʾ���ı�����
				ta.append(tf.getText() + '\n');
				ta.setSelectionEnd(ta.getText().length());
				tf.setText(""); // ���ı������
			}
		});
	}
	void getserver() {
		try {
			server = new ServerSocket(8998); // ʵ����Socket����
			System.out.println("�������׽����Ѿ������ɹ�"); // �����Ϣ
			while (true) { // ����׽���������״̬
				System.out.println("�ȴ��ͻ���������"); // �����Ϣ
				socket = server.accept(); // ʵ����Socket����
				connect();
				reader = new BufferedReader(new InputStreamReader(socket
						.getInputStream())); // ʵ����BufferedReader����
				getClientMessage(); // ����getClientMessage()����
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}
	
	private void getClientMessage() {
		try {
			while (true) { // ����׽���������״̬
				if (reader.ready()) {
					// ��ÿͻ�����Ϣ
					//System.out.println("�ͻ���:" + reader.readLine());
					ta.append("�ͻ���:" + reader.readLine()+"\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		try {
			if (reader != null) {
				reader.close(); // �ر���
			}
			if (socket != null) {
				socket.close(); // �ر��׽���
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void connect() { // �����׽��ַ���
		try { // ��׽�쳣
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}
	
	public static void main(String[] args) { // ������
		MyTcp tcp = new MyTcp("����������"); // �����������
		tcp.setSize(400, 400); // ���ô����С
		tcp.setVisible(true); // ��������ʾ
		tcp.getserver(); // ���÷���
	}
}

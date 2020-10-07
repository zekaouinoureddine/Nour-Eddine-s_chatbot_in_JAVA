/*
 * author; @NourEddineZEKAOUI
 * Pject : A simple chatbot with an 
 *         awesome dialog box !
 */

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;

@SuppressWarnings("serial")
public class Chat extends JFrame implements KeyListener {

	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(50, 20);
	JTextArea input = new JTextArea(1, 20);
	JButton btnSend = new JButton();
	JLabel jLabel1 = new JLabel();
	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	String[][] chatBot = {

			// stutation
			{ "hi", "hello", "hola", "ola", "howdy", "bjr", "bonour" },
			{ "hi", "hello", "hey", "hey! great to see you!" },
			// question salutation
			{ "how are you", "how r you", "how r u", "how are u" }, { "good, thanks", "doing well" },
			// yes
			{ "yes" }, { "no", "NO", "NO!!!!!!!" },
			// default
			{ "shut up", "you're bad", "noob", "stop talking", "(Nour Eddine's chatBot is unavailable, due to LOL)" } };

	public Chat() {

		// panel title
		super("Nour Eddine ZEKAOUI");
		p.setBorder(BorderFactory.createTitledBorder("Nour Eddine's ChatBot"));

		// Panel size
		setSize(300, 800);
		setResizable(false);

		// Exit the application using the DO_NOTHING_ON_CLOSE button
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(208, 102, 255));
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(new Color(255, 48, 48));
		add(p);

		// Send button name
		btnSend.setText("Send");
		jLabel1.setText("Message :");

		// So, that our simple window appears
		setVisible(true);

		/*
		 * Definition of an action to be executed once we click on the send button
		 */
		btnSend.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onSend(evt);
			}
		});

		/*
		 * GroupLayout is a class that can be linked to a container via the setLeyout
		 * method ( Layout management )
		 */
		GroupLayout jPanel1Layout = new GroupLayout(p);
		p.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
								.addGroup(GroupLayout.Alignment.TRAILING,

										jPanel1Layout.createSequentialGroup().addComponent(jLabel1).addGap(18, 18, 18)
												.addComponent(input, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
												.addGap(18, 18, 18).addComponent(btnSend)))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(btnSend)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1).addComponent(input, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(p, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(p, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		pack();
	}

	// This method is executed when you press the ENTER key on the keyboard.
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			input.setEditable(false);
			// -----grab quote-----------
			String quote = input.getText(); // Retrieve the message entered by the user
			input.setText("");
			addText("-->You:\t" + quote);
			quote.trim(); // This method allows you to remove spaces at the beginning and at the end of
							// user INPUT
			while (quote.charAt(quote.length() - 1) == '!' || quote.charAt(quote.length() - 1) == '.'
					|| quote.charAt(quote.length() - 1) == '?') {
				quote = quote.substring(0, quote.length() - 1);
			}
			quote.trim();
			byte response = 0;
			/*
			 * 0:we're searching through chatBot[][] for matches 1:we didn't find anything
			 * 2:we did find something
			 */
			// -----check for matches----
			int j = 0;// which group we're checking
			while (response == 0) {
				if (inArray(quote.toLowerCase(), chatBot[j * 2])) {
					response = 2;
					int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);
					addText("\n-->ChatBot\t" + chatBot[(j * 2) + 1][r]);
				}
				j++;
				if (j * 2 == chatBot.length - 1 && response == 0) { // No response does not match the message received
					response = 1;
				}
			}

			// -----default--------------
			if (response == 1) {

				// No responses that match the data, responding with the default response
				int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
				addText("\n-->ChatBot\t" + chatBot[chatBot.length - 1][r]);
			}

			addText("\n");

		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	// This method is to add the message entered in the dialog box
	public void addText(String str) {
		dialog.setText(dialog.getText() + str);
	}

	public boolean inArray(String in, String[] str) {
		boolean match = false;
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(in)) {
				match = true;
			}
		}
		return match;
	}

	private void onSend(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_onSend
		input.setEditable(true);
		// -----grab quote-----------
		String quote = input.getText();
		input.setText("");
		addText("-->You:\t" + quote);

		quote.trim();
		while (quote.charAt(quote.length() - 1) == '!' || quote.charAt(quote.length() - 1) == '.'
				|| quote.charAt(quote.length() - 1) == '?') {
			quote = quote.substring(0, quote.length() - 1);
		}
		quote.trim();
		byte response = 0;
		int j = 0;// which group we're checking
		while (response == 0) {
			if (inArray(quote.toLowerCase(), chatBot[j * 2])) {
				response = 2;
				int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);
				addText("\n-->ChatBot\t" + chatBot[(j * 2) + 1][r]);
			}
			j++;
			if (j * 2 == chatBot.length - 1 && response == 0) {
				response = 1;
			}
		}

		// -----default--------------
		if (response == 1) {
			int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
			addText("\n-->ChatBot\t" + chatBot[chatBot.length - 1][r]);
		}
		addText("\n");

	}

	public static void main(String[] args) {
		try {
			new Chat();
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
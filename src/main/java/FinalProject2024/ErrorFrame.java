package FinalProject2024;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class ErrorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel errorLabel;

	/**
	 * Create the frame.
	 */
	public ErrorFrame(String errorText) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		this.errorLabel = new JLabel(errorText, SwingConstants.CENTER);
		this.errorLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		this.errorLabel.setForeground(Color.RED);
		contentPane.add(this.errorLabel);
		
		setContentPane(contentPane);
	}
}

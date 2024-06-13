package FinalProject2024;

import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import java.awt.Label;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class StudentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Label labelLastName;
	private Label labelFirstName;
	private Label labelID;
	private Label labelGrade;
	private JTextField textFieldLastName;
	private JTextField textFieldFirstName;
	private JTextField textFieldID;
	private ButtonGroup gradeGroup;
	private JButton buttonSave;

	/**
	 * Create the frame.
	 */
	public StudentFrame(Student1 student) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		this.labelLastName = new Label("Last Name");
		this.labelLastName.setFont(new Font("Dialog", Font.BOLD, 12));
		this.labelLastName.setBounds(11, 12, 65, 14);
		contentPane.add(this.labelLastName);
		
		this.labelFirstName = new Label("First Name");
		this.labelFirstName.setFont(new Font("Dialog", Font.BOLD, 12));
		this.labelFirstName.setBounds(11, 34, 65, 14);
		contentPane.add(this.labelFirstName);

		this.labelID = new Label("ID");
		this.labelID.setFont(new Font("Dialog", Font.BOLD, 12));
		this.labelID.setBounds(11, 56, 65, 14);
		contentPane.add(this.labelID);
	
		this.labelGrade = new Label("Grade");
		this.labelGrade.setFont(new Font("Dialog", Font.BOLD, 12));
		this.labelGrade.setBounds(11, 80, 65, 14);
		contentPane.add(this.labelGrade);

		this.textFieldLastName = new JTextField();
		this.textFieldLastName.setBounds(82, 10, 176, 20);
		this.textFieldLastName.setColumns(10);
		this.textFieldLastName.setText(student.getLastName());
		contentPane.add(this.textFieldLastName);

		this.textFieldFirstName = new JTextField();
		this.textFieldFirstName.setBounds(82, 32, 176, 20);
		this.textFieldFirstName.setColumns(10);
		this.textFieldFirstName.setText(student.getFirstName());
		contentPane.add(this.textFieldFirstName);

		this.textFieldID = new JTextField();
		this.textFieldID.setBounds(82, 54, 176, 20);
		this.textFieldID.setColumns(10);
		this.textFieldID.setText(student.getId());
		contentPane.add(this.textFieldID);
		
		this.gradeGroup = new ButtonGroup();
		
		JRadioButton radioButton9 = new JRadioButton("9");
		radioButton9.setBounds(80, 78, 44, 23);
		this.gradeGroup.add(radioButton9);
		contentPane.add(radioButton9);
		
		JRadioButton radioButton10 = new JRadioButton("10");
		radioButton10.setBounds(124, 78, 44, 23);
		this.gradeGroup.add(radioButton10);
		contentPane.add(radioButton10);

		JRadioButton radioButton11 = new JRadioButton("11");
		radioButton11.setBounds(168, 78, 44, 23);
		this.gradeGroup.add(radioButton11);
		contentPane.add(radioButton11);

		JRadioButton radioButton12 = new JRadioButton("12");
		radioButton12.setBounds(212, 78, 44, 23);
		this.gradeGroup.add(radioButton12);
		contentPane.add(radioButton12);

		for (Enumeration<AbstractButton> buttons = gradeGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton currentButton = buttons.nextElement();
			if (currentButton.getText().equals(student.getGrade())) {
				gradeGroup.setSelected(currentButton.getModel(), true);
				break;
			}
		}

		this.buttonSave = new JButton("Save");
		this.buttonSave.setBounds(11, 265, 150, 23);
		this.buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				student.setLastName(textFieldLastName.getText());
				student.setFirstName(textFieldFirstName.getText());
				student.setId(textFieldID.getText());
				student.setGrade(RegistrationFrame.getSelectedRadioButton(gradeGroup).getText());
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(this.buttonSave);
	}
}

package FinalProject2024;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.function.Function;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.awt.Label;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Label labelLastName;
	private Label labelFirstName;
	private Label labelID;
	private Label labelGrade;
	private JTextField textFieldLastName;
	private JTextField textFieldFirstName;
	private JTextField textFieldID;
	private JTextField textFieldSearch;
	private ButtonGroup gradeGroup;
	private JButton buttonAdd;
	private JButton buttonSearch;
	private JButton buttonPerformSearch;
	private JButton buttonSave;
	private JComboBox comboBoxSearch;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<Student1> myStudents;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationFrame frame = new RegistrationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		myStudents = new ArrayList<Student1>();
		String[] headings = { "Last Name", "First Name", "ID", "Grade" };
		
		this.model = new DefaultTableModel(headings, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
			   return false;
			}
		};
		this.loadFile();
		
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
		contentPane.add(this.textFieldLastName);

		this.textFieldFirstName = new JTextField();
		this.textFieldFirstName.setBounds(82, 32, 176, 20);
		this.textFieldFirstName.setColumns(10);
		contentPane.add(this.textFieldFirstName);

		this.textFieldID = new JTextField();
		this.textFieldID.setBounds(82, 54, 176, 20);
		this.textFieldID.setColumns(10);
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
		
		this.buttonAdd = new JButton("Add Student");
		this.buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedButton = "";
				String[] fields;
				int index;
				for (Enumeration<AbstractButton> buttons = gradeGroup.getElements(); buttons.hasMoreElements();) {
					AbstractButton currentButton = buttons.nextElement();
					if (currentButton.isSelected()) {
						selectedButton = currentButton.getText();
						break;
					}
				}
				fields = new String[] { textFieldLastName.getText(), textFieldFirstName.getText(), textFieldID.getText(), selectedButton };
				if (!checkEmpty(fields)) {
					return;
				}
				Student1 newStudent = new Student1(fields);
				index = findLocationToAdd(newStudent);
				myStudents.add(index, newStudent);
				model.insertRow(index, newStudent.getStudentInfo());
				textFieldLastName.setText("");
				textFieldFirstName.setText("");
				textFieldID.setText("");
				gradeGroup.clearSelection();
			}
		});
		this.buttonAdd.setBounds(90, 121, 150, 23);
		contentPane.add(this.buttonAdd);
		
		this.buttonSearch = new JButton("Search");
		this.buttonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonPerformSearch.setEnabled(true);
				comboBoxSearch.setEnabled(true);
				textFieldSearch.setEditable(true);
			}
		});
		this.buttonSearch.setBounds(11, 200, 150, 23);
		contentPane.add(this.buttonSearch);

		this.buttonPerformSearch = new JButton("Perform Search");
		this.buttonPerformSearch.setBounds(11, 283, 150, 23);
		this.buttonPerformSearch.setEnabled(false);
		this.buttonPerformSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchedText = textFieldSearch.getText();
				ArrayList<Student1> searchedStudents;
				if (!checkEmpty(new String[] {(String) comboBoxSearch.getSelectedItem(), searchedText})) {
					return;
				}
				searchedStudents = findStudents(comboBoxSearch.getSelectedIndex(), searchedText);
				comboBoxSearch.setSelectedIndex(0);
				textFieldSearch.setText("");
				if (searchedStudents.size() < 1) {
					try {
						ErrorFrame notFoundFrame = new ErrorFrame("Student is not found");
						notFoundFrame.setVisible(true);
						notFoundFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 				} catch (Exception err) { 
	 					err.printStackTrace();
	 				}
					return;
				}
				try {
					StudentFrame viewStudentFrame = new StudentFrame(searchedStudents.get(0));
					viewStudentFrame.setVisible(true);
					viewStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch(Exception err) {
					err.printStackTrace();
				}
			}
		});
		contentPane.add(this.buttonPerformSearch);
		
		this.textFieldSearch = new JTextField();
		this.textFieldSearch.setBounds(11, 259, 150, 20);
		this.textFieldSearch.setEditable(false);
		contentPane.add(textFieldSearch);
		
		String[] choices = { "", "Last Name", "First Name", "ID", "Grade" };
		this.comboBoxSearch = new JComboBox(choices);
		this.comboBoxSearch.setBounds(11, 230, 150, 22);
		this.comboBoxSearch.setEnabled(false);
		contentPane.add(comboBoxSearch);
		
		this.buttonSave = new JButton("Save");
		this.buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeToFile();
			}
		});
		this.buttonSave.setBounds(543, 282, 150, 23);
		contentPane.add(this.buttonSave);
		
		this.table = new JTable(model);
		this.table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(369, 11, 326, 264);
		contentPane.add(scrollPane);
	}
	
	private boolean checkEmpty(String[] fields) {
		for (String item : fields) {
			if (item.length() < 1) {
				return false;
			}
		}
		return true;
	}
	
	private ArrayList<Student1> find(String searchFor, Function<Student1, String> getter) {
		ArrayList<Student1> list = new ArrayList<Student1>();
		for (Student1 student : this.myStudents) {
			String field = getter.apply(student);
			if (field.indexOf(searchFor) != -1) {
				list.add(student);
			}
		}
		return list;
	}
	
	private ArrayList<Student1> findStudents(int searchType, String searchFor) {
		switch (searchType) {
			case 1:
				return this.find(searchFor, Student1::getLastName);
			case 2:
				return this.find(searchFor, Student1::getFirstName);
			case 3:
				return this.find(searchFor, Student1::getId);
			case 4:
				return this.find(searchFor, Student1::getGrade);
			default:
				return new ArrayList<Student1>();
		}
	}
	
	private int findLocationToAdd(Student1 student) {
		int i = 0;
		while (i < this.myStudents.size() && student.compareTo(this.myStudents.get(i)) > 0) {
			i++;
		}
		return i;
	}
	
	private boolean isUniqueId(String id) {
		for (Student1 student : this.myStudents) {
			if (id.equals(student.getId())) {
				return false;
			}
		}
		return true;
	}
	
	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File("student.txt"));
			for (Student1 student : this.myStudents) {
				for (String info : student.getStudentInfo()) {
					writer.write(info + "\n");
				}
			}
			writer.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void loadFile() {
		try {
			Scanner infile = new Scanner(new File("student.txt"));
			while (infile.hasNextLine()) {
				String lastName = infile.nextLine();
				String firstName = infile.nextLine();
				String id = infile.nextLine();
				String grade = infile.nextLine();
				Student1 student = new Student1(lastName, firstName, id, grade);
				int index = this.findLocationToAdd(student);
				this.myStudents.add(index, student);
				this.model.insertRow(index, student.getStudentInfo());
			}
			infile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
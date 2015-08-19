package FrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxUI;

import BackEnd.Solution;
import BackEnd.VratAlgorythm;

public class firstPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	public String defaultLocationOpenFile = System.getProperty("user.dir")
			+ "\\Answers.txt";
	public String defaultLocationSaveFile = System.getProperty("user.dir")+"\\LogFiles";
	public String defaultLocationDatabaseFile = System.getProperty("user.dir");
	public JLabel notificationLabel;
	public JTextField queryItem1;
	private JTextField queryItem2;
	private JTextField queryItem3;
	private JTextField answer;
	private JTextField changeFileSource;
	private JTextField comratVAnswer;
	private JLabel queryLabel2;
	private JLabel queryLabel3;
	private JLabel queryLabel1;
	private JLabel answerLabel;
	private JLabel comratVAnswerLabel;
	private JButton changeFile;
	private JButton start;
	private JButton next;
	private JButton previous;
	private JButton saveFile;
	private VratAlgorythm algorythm;
	private Solution soln;
	private int queryNumber;
	private JComboBox<String> changeDatabase;

	public firstPanel() {
		initializePanel();
		addListeners();
		changePositionMain(35, -50, 0, 0);
		changePosition(40, 40, 0, 0);
	}

	public JPanel getPanel() {
		return panel_1;
	}

	private void initializePanel() {
		queryNumber = 0;

		Font uniformTextFont = new Font("Sitka Subheading", Font.BOLD, 14);
		Font uniformLabelFont = new Font("Sitka Subheading", Font.BOLD, 16);
		Color backGround = Color.DARK_GRAY;
		Color foreGround = Color.lightGray;
		panel_1 = new JPanel();
		notificationLabel = new JLabel("Welcome");
		notificationLabel.setForeground(Color.GREEN);
		notificationLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		notificationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notificationLabel.setBackground(Color.darkGray);
		notificationLabel.setBounds(235, 30, 455, 50);
		notificationLabel.setVisible(true);
		notificationLabel.setOpaque(true);
		panel_1.add(notificationLabel);


		/***************************************************************************************/
		String[] tablesList = { "ComratV", "ComratC" };
		ImageIcon dropIcon = new ImageIcon("Images/Icons/dropdown.png");
		changeDatabase = new JComboBox<>(tablesList);
		changeDatabase.setBounds(252, 390, 356, 35);
		changeDatabase.setForeground(foreGround);
		changeDatabase.setToolTipText("Select the knowlegdebase for comrat-V");
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		changeDatabase.setRenderer(dlcr);

		UIManager.put("ComboBox.background", backGround);
		UIManager.put("ComboBox.foreground", foreGround);
		UIManager.put("ComboBox.selectionBackground", Color.GRAY);
		UIManager.put("ComboBox.selectionForeground", foreGround);
		changeDatabase.setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton btn = new JButton();
				btn.setIcon(dropIcon);
				btn.setBackground(Color.black);
				return btn;
			}
		});
		
		panel_1.add(changeDatabase);
		changeFileSource = new JTextField();
		changeFileSource.setBounds(252, 330, 356, 50);
		changeFileSource.setColumns(10);
		changeFileSource.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		changeFileSource.setBackground(backGround);
		changeFileSource.setForeground(foreGround);
		changeFileSource.setHorizontalAlignment(SwingConstants.CENTER);
		changeFileSource.setText(defaultLocationOpenFile);
		changeFileSource.setEditable(false);
		panel_1.add(changeFileSource);

		changeFile = new JButton("Change File");
		changeFile.setForeground(Color.BLACK);
		changeFile.setFont(uniformTextFont);
		changeFile.setBounds(41, 338, 157, 31);
		panel_1.add(changeFile);

		
		start = new JButton("Solve");
		start.setForeground(Color.BLACK);
		start.setBounds(646, 338, 157, 31);
		start.setMnemonic('N');
		//KeyStroke x=KeyStroke.getKeyStroke("control N");
		start.setMnemonic('R');
		panel_1.add(start);
		
		// ImageIcon saveIcon=new ImageIcon("Images/Icons/save.png");
		saveFile = new JButton("Save Log File");
		// saveFile.setIcon(saveIcon);
		saveFile.setHorizontalTextPosition(SwingConstants.LEFT);
		saveFile.setForeground(Color.BLACK);
		saveFile.setBounds(646, 372, 157, 31);
		saveFile.setVisible(false);

		
		/******************************************************************************************/

		queryItem1 = new JTextField();
		queryItem1.setFont(uniformTextFont);
		queryItem1.setBackground(backGround);
		queryItem1.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem1.setForeground(foreGround);
		queryItem1.setBounds(41, 225, 157, 31);
		queryItem1.setColumns(10);
		panel_1.add(queryItem1);

		queryItem2 = new JTextField();
		queryItem2.setFont(uniformTextFont);
		queryItem2.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem2.setBackground(backGround);
		queryItem2.setForeground(foreGround);
		queryItem2.setBounds(252, 225, 157, 31);
		panel_1.add(queryItem2);
		queryItem2.setColumns(10);

		queryItem3 = new JTextField();
		queryItem3.setFont(uniformTextFont);
		queryItem3.setBackground(backGround);
		queryItem3.setForeground(foreGround);
		queryItem3.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem3.setBounds(451, 225, 157, 31);
		queryItem3.setColumns(10);
		panel_1.add(queryItem3);

		answer = new JTextField();
		answer.setFont(uniformTextFont);
		answer.setBackground(backGround);
		answer.setHorizontalAlignment(SwingConstants.CENTER);
		answer.setForeground(Color.WHITE);
		answer.setBounds(646, 225, 157, 31);
		answer.setColumns(10);
		panel_1.add(answer);

		queryLabel1 = new JLabel("First Item");
		queryLabel1.setFont(uniformLabelFont);
		queryLabel1.setBackground(new Color(0, 0, 0));
		queryLabel1.setLabelFor(queryItem1);
		queryLabel1.setForeground(Color.BLACK);
		queryLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		queryLabel1.setBounds(41, 190, 157, 31);
		panel_1.add(queryLabel1);

		queryLabel2 = new JLabel("Second Item");
		queryLabel2.setFont(uniformLabelFont);
		queryLabel2.setLabelFor(queryItem2);
		queryLabel2.setForeground(Color.BLACK);
		queryLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		queryLabel2.setBounds(252, 190, 157, 31);
		panel_1.add(queryLabel2);
		// frame.getContentPane().add(queryLabel2);

		queryLabel3 = new JLabel("Third Item");
		queryLabel3.setFont(uniformLabelFont);
		queryLabel3.setLabelFor(queryItem3);
		queryLabel3.setForeground(Color.BLACK);
		queryLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		queryLabel3.setBounds(451, 190, 157, 31);
		panel_1.add(queryLabel3);
		// frame.getContentPane().add(queryLabel3);

		answerLabel = new JLabel("Correct Answer");
		answerLabel.setFont(uniformLabelFont);
		answerLabel.setForeground(Color.BLACK);
		answerLabel.setLabelFor(answer);
		answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(answerLabel);
		answerLabel.setBounds(646, 190, 157, 31);
		// frame.getContentPane().add(answerLabel);
		// ******************************************************************//

		comratVAnswer = new JTextField();
		comratVAnswer.setBackground(Color.DARK_GRAY);
		comratVAnswer.setForeground(Color.WHITE);
		comratVAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		comratVAnswer.setFont(uniformTextFont);
		comratVAnswer.setBounds(270, 286, 336, 31);
		comratVAnswer.setColumns(10);
		panel_1.add(comratVAnswer);
		// frame.getContentPane().add(comratVAnswer);

		comratVAnswerLabel = new JLabel("VisualRat Answer");
		comratVAnswerLabel.setFont(uniformLabelFont);
		comratVAnswerLabel.setForeground(Color.BLACK);
		comratVAnswerLabel.setLabelFor(comratVAnswer);
		comratVAnswerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// lblVisualratAnswer.setBackground(new Color(0, 0, 0));
		comratVAnswerLabel.setBounds(105, 286, 157, 41);
		panel_1.add(comratVAnswerLabel);

		ImageIcon backIcon = new ImageIcon("Images/Icons/back.png");
		// frame.getContentPane().add(lblVisualratAnswer);
		previous = new JButton();
		// previous.setVisible(true);
		previous.setEnabled(false);
		previous.setIcon(backIcon);
		previous.setToolTipText("Previous Answer");
		previous.setBackground(new Color(51, 51, 51));
		previous.setHorizontalAlignment(SwingConstants.CENTER);
		previous.setBounds(665, 286, 50, 31);
		previous.setForeground(Color.BLACK);
		previous.setMnemonic(KeyEvent.VK_LEFT);

		panel_1.add(previous);

		ImageIcon nextIcon = new ImageIcon("Images/Icons/next.png");
		next = new JButton();
		// next.setVisible(true);
		next.setIcon(nextIcon);
		next.setHorizontalAlignment(SwingConstants.CENTER);
		next.setEnabled(false);
		next.setBackground(new Color(51, 51, 51));
		next.setToolTipText("Next Answer");
		next.setBounds(729, 286, 50, 31);
		next.setForeground(Color.black);
		next.setMnemonic(KeyEvent.VK_RIGHT);
		panel_1.add(next);
		// String questionNumber1="";
		// ******************************************************************//


		panel_1.add(saveFile);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);

		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.push(new EventQueueProxy());
		/*
		 * Thread.setDefaultUncaughtExceptionHandler(new
		 * Thread.UncaughtExceptionHandler() { public void
		 * uncaughtException(Thread t, Throwable e) {
		 * System.out.println(e.getMessage()); setErrorMessage(e.getMessage());
		 * e.printStackTrace(); } });
		 */
	}

	private void addListeners(){

		changeDatabase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notificationLabel.setText(changeDatabase.getSelectedItem()
						.toString());
			}
		});

		changeFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileExplorer = new JFileChooser(
						defaultLocationOpenFile);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Text & Excel Files", "txt", "xls");
				fileExplorer.setFileFilter(filter);
				int returnVal = fileExplorer.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					reset();
					// setSuccessMessage("Please Select a text file with Proper Format(Read Manual)");
					defaultLocationOpenFile = fileExplorer.getSelectedFile()
							.getPath();
					start.setEnabled(true);
					if (!(defaultLocationOpenFile.contains(".txt"))) {
						setMessage("Invalid File. Please select a text File", 1);
						start.setEnabled(false);
					}
					changeFileSource.setText(fileExplorer.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});

		notificationLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				notificationLabel.setVisible(false);
			}
		});

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				algorythm = new VratAlgorythm();
				algorythm.textFile
						.setReadTextFileSource(defaultLocationOpenFile);
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						algorythm = new VratAlgorythm();
						algorythm.textFile
								.setReadTextFileSource(defaultLocationOpenFile);
						soln = algorythm.solve(null);
						if (soln != null) {
							while (soln.getSize() < 3) {
								Thread.sleep(50);
								setMessage("Solving....", 0);
							}
						}
						return null;
					}

					@Override
					protected void done() {
						if (soln != null) {
							next.setEnabled(true);
							// ///next.setVisible(true);
							saveFile.setVisible(true);
							showResults(0);
						} else {
							setMessage(
									"Invalid File.Please check the fileName and its Contents",
									1);
						}
					}
				};
				worker.execute();
			}
		});
		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(
						defaultLocationSaveFile);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Text & Excel Files", "txt", "xls");
				fileChooser.setFileFilter(filter);
				fileChooser.setDialogTitle("Specify a file to save");
				int userSelection = fileChooser.showSaveDialog(panel_1);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					SwingWorker<Void, Void> saveFileInBackground = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {
							File fileToSave = fileChooser.getSelectedFile();
							defaultLocationSaveFile = fileToSave
									.getAbsolutePath();
							algorythm.textFile
									.setWriteTextFileSource(defaultLocationSaveFile);
							algorythm.textFile.writeLog((soln.toString()));
							algorythm.excel.write(soln.getSolution());
							return null;
						}

						@Override
						protected void done() {
							setMessage("File saved successfully", 0);
						}
					};
					saveFileInBackground.execute();

				}

			}
		});
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showResults(0);
				previous.setEnabled(true);
			}
		});

		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showResults(1);
				next.setEnabled(true);
			}
		});
	}
	private void changePositionMain(int x, int y, int width, int height) {
		queryItem1.setBounds(queryItem1.getX() + x, queryItem1.getY() + y,
				queryItem1.getWidth() + width, queryItem1.getHeight() + height);
		queryItem2.setBounds(queryItem2.getX() + x, queryItem2.getY() + y,
				queryItem2.getWidth() + width, queryItem2.getHeight() + height);
		queryItem3.setBounds(queryItem3.getX() + x, queryItem3.getY() + y,
				queryItem3.getWidth() + width, queryItem3.getHeight() + height);
		queryLabel1.setBounds(queryLabel1.getX() + x, queryLabel1.getY() + y,
				queryLabel1.getWidth() + width, queryLabel1.getHeight()
						+ height);
		queryLabel2.setBounds(queryLabel2.getX() + x, queryLabel2.getY() + y,
				queryLabel2.getWidth() + width, queryLabel2.getHeight()
						+ height);
		queryLabel3.setBounds(queryLabel3.getX() + x, queryLabel3.getY() + y,
				queryLabel3.getWidth() + width, queryLabel3.getHeight()
						+ height);
		answer.setBounds(answer.getX() + x, answer.getY() + y,
				answer.getWidth() + width, answer.getHeight() + height);
		answerLabel.setBounds(answerLabel.getX() + x, answerLabel.getY() + y,
				answerLabel.getWidth() + width, answerLabel.getHeight()
						+ height);
		next.setBounds(next.getX() + x, next.getY() + y, next.getWidth()
				+ width, next.getHeight() + height);
		previous.setBounds(previous.getX() + x, previous.getY() + y,
				previous.getWidth() + width, previous.getHeight() + height);
		comratVAnswer.setBounds(comratVAnswer.getX() + x, comratVAnswer.getY() + y,
				comratVAnswer.getWidth() + width, comratVAnswer.getHeight());
		comratVAnswerLabel.setBounds(comratVAnswerLabel.getX() + x,
				comratVAnswerLabel.getY() + y, comratVAnswerLabel.getWidth()
						+ width, comratVAnswerLabel.getHeight());
	}

	private void changePosition(int x, int y, int width, int height) {
		changeFileSource.setBounds(changeFileSource.getX() + x,
				changeFileSource.getY() + y, changeFileSource.getWidth()
						+ width, changeFileSource.getHeight()+height);
		changeFile.setBounds(changeFile.getX() + x,
				changeFile.getY() + y, changeFile.getWidth()
						+ width, changeFile.getHeight());
		start.setBounds(start.getX() + x, start.getY() + y, start.getWidth()
				+ width, start.getHeight());
		saveFile.setBounds(saveFile.getX() + x, saveFile.getY() + y, saveFile.getWidth()
				+ width, saveFile.getHeight());
		changeDatabase.setBounds(changeDatabase.getX() + x,
				changeDatabase.getY() + y, changeDatabase.getWidth() + width,
				changeDatabase.getHeight());

	}

	private void reset() {
		notificationLabel.setText(null);
		notificationLabel.setVisible(false);
		queryItem1.setText(null);
		queryItem2.setText(null);
		queryItem3.setText(null);
		answer.setText(null);
		comratVAnswer.setText(null);
		next.setEnabled(false);
		// next.setVisible(false);
		saveFile.setVisible(false);
		queryNumber = 0;
		algorythm = null;
		soln = null;
	}

	private void setMessage(String txt, int option) {
		if (txt.length() > 41) {
			notificationLabel.setFont(new Font("Sitka Subheading", Font.BOLD,
					16));
		}
		notificationLabel.setVisible(true);
		if (option == 1) {
			notificationLabel.setForeground(Color.red);
		} else {
			notificationLabel.setForeground(Color.GREEN);
		}
		notificationLabel.setText(txt);
	}

	private void showResults(int option) {// 0 for next and 1 for previous
		if (option == 0) {
			++queryNumber;
		} else if (option == 1) {
			--queryNumber;
		}
		queryItem1.setText(soln.getSolution().get(queryNumber).firstItem);
		queryItem2.setText(soln.getSolution().get(queryNumber).secondItem);
		queryItem3.setText(soln.getSolution().get(queryNumber).thirdItem);
		answer.setText(soln.getSolution().get(queryNumber).correctAnswer);
		if (soln.getSolution().get(queryNumber).getCorrect() == 1) {
			setMessage("Q" + queryNumber + ": Correct Answer By Vrat", 0);
		} else if (soln.getSolution().get(queryNumber).getCorrect() == -1) {
			setMessage("Q" + queryNumber + ": Incorrect Answer", 1);
		} else {
			setMessage("Q" + queryNumber + ": No Convergence", 1);
		}
		comratVAnswer.setText(soln.getSolution().get(queryNumber)
				.getConvergedAnswer().keySet().toString());
		System.out.println(queryNumber);

		if (queryNumber >= soln.getSolution().size()) {
			next.setEnabled(false);
		} else if (queryNumber < 2) {
			previous.setEnabled(false);
		}
	}
}

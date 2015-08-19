package FrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class thirdPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	public String defaultLocationOpenFile = System.getProperty("user.dir")
			+ "\\Answers.txt";
	public String defaultLocationSaveFile = System.getProperty("user.dir");
	public String defaultLocationDatabaseFile=System.getProperty("user.dir");
	public JLabel notificationLabel;
	public JTextField queryItem1;
	private JTextField queryItem2;
	private JTextField queryItem3;
	private JTextField answer;
	private JTextField changeFileSource;
	private JTextField vRatAnswer;
	private JLabel query2Label;
	private JLabel query3Label;
	private JLabel query1Label;
	private JLabel answerLabel;
	private JLabel lblVisualratAnswer;
	private JButton changeFile;
	private JButton start;
	private JButton next;
	private JButton previous;
	private JButton saveFile;
	private VratAlgorythm algorythm;
	private Solution soln;
	private int queryNumber ;
	private JComboBox<String> changeDatabase;

	public thirdPanel() {
		initializePanel();
	}

	public JPanel getPanel() {
		return panel_1;
	}

	private void initializePanel() {
		queryNumber=0;
		
		Font uniformTextFont = new Font("Sitka Subheading", Font.BOLD, 14);
		Font uniformLabelFont = new Font("SketchFlow Print", Font.BOLD, 16);
		Color backGround=Color.DARK_GRAY;
		Color foreGround=Color.lightGray;
		panel_1 = new JPanel();
		notificationLabel = new JLabel("WelCome");
		notificationLabel.setForeground(Color.GREEN);
		notificationLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		notificationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notificationLabel.setBackground(Color.darkGray);
		notificationLabel.setBounds(200, 30, 455, 50);
		notificationLabel.setVisible(true);
		notificationLabel.setOpaque(true);
		panel_1.add(notificationLabel);

		String []tablesList={"ComratV","ComratC"};
		ImageIcon dropIcon=new ImageIcon("Images/Icons/dropdown.png");
		changeDatabase= new JComboBox<>(tablesList);
		changeDatabase.setBounds(252,130,356,35);
		changeDatabase.setForeground(foreGround);
		changeDatabase.setToolTipText("Select the knowlegdebase for comrat-V");
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
		changeDatabase.setRenderer(dlcr); 
		
		UIManager.put("ComboBox.background", backGround);
		UIManager.put("ComboBox.foreground", foreGround);
		UIManager.put("ComboBox.selectionBackground",Color.GRAY);
		UIManager.put("ComboBox.selectionForeground",foreGround);
		changeDatabase.setUI(new BasicComboBoxUI() {
			   @Override
			   protected JButton createArrowButton() {
				   JButton btn =new JButton();
				   btn.setIcon(dropIcon);
				   btn.setBackground(Color.black);
			      return btn;
			   }
			});
		panel_1.add(changeDatabase);


/***************************************************************************************/
	changeFileSource = new JTextField();
		changeFileSource.setBounds(252, 190, 356, 50);
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
		changeFile.setBounds(41, 200, 157, 31);
		panel_1.add(changeFile);
		
		//ImageIcon saveIcon=new ImageIcon("Images/Icons/save.png");
		saveFile = new JButton("Save Log File");
	//	saveFile.setIcon(saveIcon);
		saveFile.setHorizontalTextPosition(SwingConstants.LEFT);
		saveFile.setForeground(Color.BLACK);
		saveFile.setBounds(646, 165, 157, 31);
		saveFile.setVisible(false);

		start = new JButton("Solve");
		start.setForeground(Color.BLACK);
		start.setBounds(646, 207, 157, 31);
		panel_1.add(start);
/******************************************************************************************/

		queryItem1 = new JTextField();
		queryItem1.setFont(uniformTextFont);
		queryItem1.setBackground(backGround);
		queryItem1.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem1.setForeground(foreGround);
		queryItem1.setBounds(41, 328, 157, 31);
		queryItem1.setColumns(10);
		panel_1.add(queryItem1);

		queryItem2 = new JTextField();
		queryItem2.setFont(uniformTextFont);
		queryItem2.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem2.setBackground(backGround);
		queryItem2.setForeground(foreGround);
		queryItem2.setBounds(252, 328, 157, 31);
		panel_1.add(queryItem2);
		queryItem2.setColumns(10);

		queryItem3 = new JTextField();
		queryItem3.setFont(uniformTextFont);
		queryItem3.setBackground(backGround);
		queryItem3.setForeground(foreGround);
		queryItem3.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem3.setBounds(451, 328, 157, 31);
		queryItem3.setColumns(10);
		panel_1.add(queryItem3);

		answer = new JTextField();
		answer.setFont(uniformTextFont);
		answer.setBackground(backGround);
		answer.setHorizontalAlignment(SwingConstants.CENTER);
		answer.setForeground(Color.WHITE);
		answer.setBounds(646, 328, 157, 31);
		answer.setColumns(10);
		panel_1.add(answer);

		query1Label = new JLabel("First Item");
		query1Label.setFont(uniformLabelFont);
		query1Label.setBackground(new Color(0, 0, 0));
		query1Label.setLabelFor(queryItem1);
		query1Label.setForeground(Color.BLACK);
		query1Label.setHorizontalAlignment(SwingConstants.CENTER);
		query1Label.setBounds(41, 286, 157, 31);
		panel_1.add(query1Label);

		query2Label = new JLabel("Second Item");
		query2Label.setFont(uniformLabelFont);
		query2Label.setLabelFor(queryItem2);
		query2Label.setForeground(Color.BLACK);
		query2Label.setHorizontalAlignment(SwingConstants.CENTER);
		query2Label.setBounds(252, 286, 157, 31);
		panel_1.add(query2Label);
		// frame.getContentPane().add(query2Label);

		query3Label = new JLabel("Third Item");
		query3Label.setFont(uniformLabelFont);
		query3Label.setLabelFor(queryItem3);
		query3Label.setForeground(Color.BLACK);
		query3Label.setHorizontalAlignment(SwingConstants.CENTER);
		query3Label.setBounds(451, 286, 157, 31);
		panel_1.add(query3Label);
		// frame.getContentPane().add(query3Label);

		answerLabel = new JLabel("Correct Answer");
		answerLabel.setFont(uniformLabelFont);
		answerLabel.setForeground(Color.BLACK);
		answerLabel.setLabelFor(answer);
		answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(answerLabel);
		answerLabel.setBounds(646, 286, 157, 31);
		// frame.getContentPane().add(answerLabel);
		// ******************************************************************//

		vRatAnswer = new JTextField();
		vRatAnswer.setBackground(Color.DARK_GRAY);
		vRatAnswer.setForeground(Color.WHITE);
		vRatAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		vRatAnswer.setFont(uniformTextFont);
		vRatAnswer.setBounds(270, 429, 336, 31);
		vRatAnswer.setColumns(10);
		panel_1.add(vRatAnswer);
		// frame.getContentPane().add(vRatAnswer);

		lblVisualratAnswer = new JLabel("VisualRat Answer");
		lblVisualratAnswer.setFont(uniformLabelFont);
		lblVisualratAnswer.setForeground(Color.BLACK);
		lblVisualratAnswer.setLabelFor(vRatAnswer);
		lblVisualratAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		// lblVisualratAnswer.setBackground(new Color(0, 0, 0));
		lblVisualratAnswer.setBounds(105, 428, 157, 41);
		panel_1.add(lblVisualratAnswer);
		
		ImageIcon backIcon=new ImageIcon("Images/Icons/back.png");
		// frame.getContentPane().add(lblVisualratAnswer);
		previous=new JButton();
		//previous.setVisible(true);
		previous.setEnabled(false);
		previous.setIcon(backIcon);
		previous.setToolTipText("Previous Answer");
		previous.setBackground(new Color(51,51,51));
		previous.setHorizontalAlignment(SwingConstants.CENTER);
		previous.setBounds(665, 429, 50, 31);
		previous.setForeground(Color.BLACK);
		panel_1.add(previous);
		
		
		ImageIcon nextIcon=new ImageIcon("Images/Icons/next.png");
		next = new JButton();
		//next.setVisible(true);
		next.setIcon(nextIcon);
		next.setHorizontalAlignment(SwingConstants.CENTER);
		next.setEnabled(false);
		next.setBackground(new Color(51,51,51));
		next.setToolTipText("Next Answer");
		next.setBounds(729, 429, 50, 31);
		next.setForeground(Color.black);
		panel_1.add(next);
		// String questionNumber1="";

		// ******************************************************************//
		
		changeDatabase.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notificationLabel.setText(changeDatabase.getSelectedItem().toString());
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
							while (soln.getSize() <3) {
								Thread.sleep(50);
								setMessage("Solving....",0);
							}
						}
						return null;
					}

					@Override
					protected void done() {
						if (soln != null) {
							next.setEnabled(true);
							/////next.setVisible(true);
							saveFile.setVisible(true);
							showResults(0);
						} else {
							setMessage("Invalid File.Please check the fileName and its Contents",1);
						}
					}
				};
				worker.execute();
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
					
					//setSuccessMessage("Please Select a text file with Proper Format(Read Manual)");
					defaultLocationOpenFile = fileExplorer.getSelectedFile()
							.getPath();
					start.setEnabled(true);
					if(!(defaultLocationOpenFile.contains(".txt"))){
						setMessage("Invalid File. Please select a text File",1);
						start.setEnabled(false);
					}
					changeFileSource.setText(fileExplorer.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});


		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(
						defaultLocationSaveFile);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Text files", "txt");
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
							algorythm.excel.write(soln.toExcel());
							return null;
						}

						@Override
						protected void done() {
							setMessage("File saved successfully",0);
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
		
		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.push(new EventQueueProxy());
		/*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(e.getMessage());
				setErrorMessage(e.getMessage());
				e.printStackTrace();
			}
		});*/
		panel_1.add(saveFile);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
	}
	

	private void reset() {
		notificationLabel.setText(null);
		notificationLabel.setVisible(false);
		queryItem1.setText(null);
		queryItem2.setText(null);
		queryItem3.setText(null);
		answer.setText(null);
		vRatAnswer.setText(null);
		next.setEnabled(false);
		//next.setVisible(false);
		saveFile.setVisible(false);
		queryNumber = 0;
		algorythm = null;
		soln = null;
	}

	private void setMessage(String txt,int option) {
		if (txt.length() > 41) {
			notificationLabel.setFont(new Font("Sitka Subheading", Font.BOLD,
					16));
		}
			notificationLabel.setVisible(true);
		if(option==1){
			notificationLabel.setForeground(Color.red);
		}else{
			notificationLabel.setForeground(Color.GREEN);
		}
		notificationLabel.setText(txt);
	}

	private void showResults(int option) {//0 for next and 1 for previous
		if(option==0){
			++queryNumber;
		}else if(option==1){
			--queryNumber;
		}
		queryItem1.setText(soln.getSolution().get(queryNumber).firstItem);
		queryItem2.setText(soln.getSolution().get(queryNumber).secondItem);
		queryItem3.setText(soln.getSolution().get(queryNumber).thirdItem);
		answer.setText(soln.getSolution().get(queryNumber).correctAnswer);
		if (soln.getSolution().get(queryNumber).getCorrect() == 1) {
			setMessage("Q"+queryNumber+": Correct Answer By Vrat",0);
		} else if (soln.getSolution().get(queryNumber).getCorrect() == -1) {
			setMessage("Q"+queryNumber+": Incorrect Answer",1);
		} else {
			setMessage("Q"+queryNumber+": No Convergence",1);
		}
		vRatAnswer.setText(soln.getSolution().get(queryNumber)
				.getConvergedAnswer().keySet().toString());
		System.out.println(queryNumber);
		
		if (queryNumber >= soln.getSolution().size()) {
			next.setEnabled(false);
		}
		else if(queryNumber<2){
			previous.setEnabled(false);
		}
	}
}

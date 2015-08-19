package FrontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import BackEnd.Solution;
import BackEnd.VratAlgorythm;

public class secondPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	public String defaultLocationOpenFile = System.getProperty("user.dir")
			+ "\\Answers.txt";
	public String defaultLocationSaveFile = System.getProperty("user.dir")
			+ "\\LogFiles";
	public String defaultLocationDatabaseFile = System.getProperty("user.dir");
	public JLabel notificationLabel;
	public JLabel queryItem1;
	private JLabel queryItem2;
	private JLabel queryItem3;
	private JLabel answer;
	// private JTextFiseld changeFileSource;
	private JLabel comratVAnswer;
	private JLabel queryLabel2;
	private JLabel queryLabel3;
	private JLabel queryLabel1;
	private JLabel answerLabel;
	private JLabel comratVAnswerLabel;
	// private JButton changeFile;
	private JButton start;
	private JButton next;
	private JButton previous;
	// private JButton saveFile;
	private VratAlgorythm algorythm;
	private Solution soln;
	private int queryNumber;

	public secondPanel() {
		initializePanel();
		addListeners();
		changePositionMain(35, -90, 0, 0);
		changePosition(0, 90, 0, 0);
	}

	public JPanel getPanel() {
		return panel_1;
	}

	private void initializePanel() {
		queryNumber = 0;

		Font uniformTextFont = new Font("Sitka Subheading", Font.BOLD, 14);
		Font uniformLabelFont = new Font("Sitka Subheading", Font.BOLD, 16);
		Color backGround = Color.DARK_GRAY;
		//Color foreGround = Color.WHITE;
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

		

		/******************************************************************************************/

		queryItem1 = new JLabel();
		queryItem1.setOpaque(true);
		queryItem1.setFont(uniformTextFont);
		queryItem1.setBackground(backGround);
		queryItem1.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem1.setForeground(Color.white);
		queryItem1.setBounds(41, 225, 157, 31);
		queryItem1.setBounds(41, 225, 180, 200);
		panel_1.add(queryItem1);

		queryItem2 = new JLabel();
		queryItem2.setOpaque(true);
		queryItem2.setFont(uniformTextFont);
		queryItem2.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem2.setBackground(backGround);
		queryItem2.setForeground(Color.white);
		queryItem2.setBounds(252, 225, 180, 200);
		panel_1.add(queryItem2);

		queryItem3 = new JLabel();
		queryItem3.setOpaque(true);
		queryItem3.setFont(uniformTextFont);
		queryItem3.setBackground(backGround);
		queryItem3.setForeground(Color.white);
		queryItem3.setHorizontalAlignment(SwingConstants.CENTER);
		queryItem3.setBounds(451, 225, 157, 31);
		queryItem3.setBounds(451, 225, 180, 200);
		panel_1.add(queryItem3);

		answer = new JLabel();
		answer.setFont(uniformTextFont);
		answer.setBackground(backGround);
		answer.setHorizontalAlignment(SwingConstants.CENTER);
		answer.setForeground(Color.WHITE);
		answer.setBounds(646, 225, 180, 200);
		answer.setOpaque(true);
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

		comratVAnswer = new JLabel();
		comratVAnswer.setBackground(Color.DARK_GRAY);
		comratVAnswer.setForeground(Color.WHITE);
		comratVAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		comratVAnswer.setFont(uniformTextFont);
		comratVAnswer.setBounds(350, 440, 180, 200);
		comratVAnswer.setOpaque(true);
		panel_1.add(comratVAnswer);
		// frame.getContentPane().add(comratVAnswer);

		comratVAnswerLabel = new JLabel("VisualRat Answer");
		comratVAnswerLabel.setFont(uniformLabelFont);
		comratVAnswerLabel.setForeground(Color.BLACK);
		comratVAnswerLabel.setLabelFor(comratVAnswer);
		comratVAnswerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// lblVisualratAnswer.setBackground(new Color(0, 0, 0));
		comratVAnswerLabel.setBounds(105, 500, 157, 41);
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
		previous.setBounds(630, 468, 50, 31);
		previous.setForeground(Color.BLACK);
		previous.setMnemonic(KeyEvent.VK_LEFT);

		panel_1.add(previous);
		start = new JButton("Solve");
		start.setForeground(Color.BLACK);
		start.setBounds(646, 348, 157, 31);
		start.setMnemonic('N');
		// KeyStroke x=KeyStroke.getKeyStroke("control N");
		start.setMnemonic('R');
		panel_1.add(start);

		ImageIcon nextIcon = new ImageIcon("Images/Icons/next.png");
		next = new JButton();
		next.setIcon(nextIcon);
		next.setHorizontalAlignment(SwingConstants.CENTER);
		next.setEnabled(false);
		next.setBackground(new Color(51, 51, 51));
		next.setToolTipText("Next Answer");
		next.setBounds(694, 468, 50, 31);
		next.setForeground(Color.black);
		next.setMnemonic(KeyEvent.VK_RIGHT);
		panel_1.add(next);
		// ******************************************************************//

		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);

		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.push(new EventQueueProxy());
	}

	private void addListeners() {

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
		comratVAnswer.setBounds(comratVAnswer.getX() + x, comratVAnswer.getY()
				+ y, comratVAnswer.getWidth() + width,
				comratVAnswer.getHeight());
		comratVAnswerLabel.setBounds(comratVAnswerLabel.getX() + x,
				comratVAnswerLabel.getY() + y, comratVAnswerLabel.getWidth()
						+ width, comratVAnswerLabel.getHeight());
	}

	private void changePosition(int x, int y, int width, int height) {

		start.setBounds(start.getX() + x, start.getY() + y, start.getWidth()
				+ width, start.getHeight());

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

	private void showResults(int option) {
		String firstItemS, thirdItemS, secondItemS, correctAnswerS, comratVAnswerS;
		// 0 for next and 1 for previous
		if (option == 0) {
			++queryNumber;
		} else if (option == 1) {
			--queryNumber;
		}
		this.queryItem1.setIcon(null);
		this.queryItem2.setIcon(null);
		this.queryItem3.setIcon(null);
		this.answer.setIcon(null);
		this.comratVAnswer.setIcon(null);

		
		firstItemS = soln.getSolution().get(queryNumber).firstItem;
		secondItemS = soln.getSolution().get(queryNumber).secondItem;
		thirdItemS = soln.getSolution().get(queryNumber).thirdItem;
		correctAnswerS = soln.getSolution().get(queryNumber).correctAnswer;
		// for(for String
		// ans:soln.getSolution().get(queryNumber).getConvergedAnswer().values())
		comratVAnswerS = soln.getSolution().get(queryNumber)
				.getConvergedAnswer().keySet().toString();
		comratVAnswerS = comratVAnswerS.replaceAll("[+.^:,\\]\\[]", "");
		secondItemS = secondItemS.replaceAll("[+.^:,//\\]\\[]", "");
		firstItemS = firstItemS.replaceAll("[+.^:,//\\]\\[]", "");
		thirdItemS = thirdItemS.replaceAll("[+.^:,//\\]\\[]", "");
		correctAnswerS = correctAnswerS.replaceAll("[+.^:,//\\]\\[]", "");

		// comratVAnswerS=comratVAnswerS.replaceAll("]","");
		if (getImage(firstItemS) == null) {

			this.queryItem1.setText(firstItemS);
		} else {
			this.queryItem1.setIcon(getImage(firstItemS));
		}
		if (getImage(secondItemS) == null) {
			this.queryItem2.setText(firstItemS);
		} else {
			this.queryItem2.setIcon(getImage(secondItemS));
		}
		if (getImage(thirdItemS) == null) {
			this.queryItem3.setText(thirdItemS);
		} else {
			this.queryItem3.setIcon(getImage(thirdItemS));
		}
		if (getImage(correctAnswerS) == null) {
			this.answer.setText(correctAnswerS);
		} else {
			this.answer.setIcon(getImage(correctAnswerS));
		}
		if (getImage(comratVAnswerS) == null) {
			this.comratVAnswer.setText(comratVAnswerS);
		} else {
			this.comratVAnswer.setIcon(getImage(comratVAnswerS));
		}

		if (soln.getSolution().get(queryNumber).getCorrect() == 1) {
			setMessage("Q" + queryNumber + ": Correct Answer By Vrat", 0);
		} else if (soln.getSolution().get(queryNumber).getCorrect() == -1) {
			setMessage("Q" + queryNumber + ": Incorrect Answer", 1);
		} else {
			setMessage("Q" + queryNumber + ": No Convergence", 1);
		}

		if (queryNumber >= soln.getSolution().size()) {
			next.setEnabled(false);
		} else if (queryNumber < 2) {
			previous.setEnabled(false);
		}
	}

	private ImageIcon getImage(String text) {
		File file;
		Image image;
		ImageIcon imageReturn;
		try {
			file = new File("ComratVPictures/" + text + ".png");
			if (file.exists() == false) {
				file = new File("ComratVPictures/" + text + ".jpg");
				if (file.exists() == false) {
					return null;
				} else {
					image = ImageIO.read(file);
					image = image.getScaledInstance(182, 200,
							Image.SCALE_DEFAULT);
					imageReturn = new ImageIcon(image);
					return imageReturn;
				}
			} else {
				image = ImageIO.read(file);
				image = image.getScaledInstance(182, 200, Image.SCALE_DEFAULT);
				imageReturn = new ImageIcon(image);
				return imageReturn;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

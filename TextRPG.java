//package textrpg;
/*Jace Tjan
 * Text RPG where user is given a story and options to interact with it
 * They are able to beat the game or die in the process.
 * User can change the text using options.
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;

//imports for xml reading
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TextRPG {

	private JFrame frmTextrpg;
	private JTextArea StoryTextArea;
	private JLabel lbCharacterStats;
	private int health;
	private int monsters;
	
	private JButton btnOption1;
	private JButton btnOption2;
	private JButton btnOption3;
	private JButton btnOption4;
	
	//tagName and these strings are used to determine
	//the next chapter based on the option clicked
	public String tagName; 
	private String op1Next;
	private String op2Next;
	private String op3Next;
	private String op4Next;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextRPG window = new TextRPG();
					window.frmTextrpg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//creates the application
	public TextRPG() {
		initialize(); //sets up the layout of the application
		
		//sets up the first chapter and displays it.
		tagName = "chapter";
		health = 20;
		monsters = 0;
		
		changeStoryText();
		changeCharacterStats();
		
	}
	
	private void changeCharacterStats() {
		//changes the label of stats
		lbCharacterStats.setText("Character health: " + String.valueOf(health));
	}
	
	
	private void changeStoryText() {
		// changes all the text on screen that is inside the xml
		try {
			File file = new File("F:\\MyowncodeProj\\textGame\\src\\textrpg\\StoryText.xml"); 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file); 
			doc.getDocumentElement().normalize();
			//they printed out root element
			NodeList nodeList = doc.getElementsByTagName(tagName);
			
			for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
				Node node = nodeList.item(itr);
				//they printed node name
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					//gets out the information as a string and puts it places.
					StoryTextArea.setText(eElement.getElementsByTagName("text").item(0).getTextContent());
					btnOption1.setText(eElement.getElementsByTagName("option1").item(0).getTextContent());
					btnOption2.setText(eElement.getElementsByTagName("option2").item(0).getTextContent());
					btnOption3.setText(eElement.getElementsByTagName("option3").item(0).getTextContent());
					btnOption4.setText(eElement.getElementsByTagName("option4").item(0).getTextContent());
					
					op1Next = eElement.getElementsByTagName("op1Next").item(0).getTextContent();
					op2Next = eElement.getElementsByTagName("op2Next").item(0).getTextContent();
					op3Next = eElement.getElementsByTagName("op3Next").item(0).getTextContent();
					op4Next = eElement.getElementsByTagName("op4Next").item(0).getTextContent();
				}
			}
			
		}catch(Exception z) {
			//was used during testing
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextrpg = new JFrame();
		frmTextrpg.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmTextrpg.setTitle("TextRPG");
		frmTextrpg.setBounds(100, 100, 668, 579);
		frmTextrpg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel for game
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(Color.GRAY);
		
		JComboBox comboFontColor = new JComboBox();
		
		comboFontColor.addItem("Black");
		comboFontColor.addItem("Red");
		comboFontColor.addItem("Blue");
		comboFontColor.addItem("Yellow");
		
		comboFontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = ((JComboBox)e.getSource()).getSelectedIndex();
				switch(index) {
					case 0: StoryTextArea.setForeground(Color.black);
							break;
					case 1: StoryTextArea.setForeground(Color.red);
							break;
					case 2: StoryTextArea.setForeground(Color.blue);
							break;
					case 3: StoryTextArea.setForeground(Color.yellow);
							break;
				}
			}
		});
		
		JComboBox comboFontSize = new JComboBox();
		
		comboFontSize.addItem("5");
		comboFontSize.addItem("10");
		comboFontSize.addItem("15");
		comboFontSize.addItem("20");
		
		comboFontSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = ((JComboBox)e.getSource()).getSelectedIndex();
				Font f = StoryTextArea.getFont();
				String name = f.getName(); 
				switch(index) {
					case 0: Font font = new Font(name, Font.BOLD, 5);
							StoryTextArea.setFont(font);
							break;
					case 1: Font font1 = new Font(name, Font.BOLD, 10);
							StoryTextArea.setFont(font1);
							break;
					case 2: Font font2 = new Font(name, Font.BOLD, 15);
							StoryTextArea.setFont(font2);
							break;
					case 3: Font font3 = new Font(name, Font.BOLD, 20);
							StoryTextArea.setFont(font3);
							break;
				}
			}
		});
		
		JLabel lblFontColor = new JLabel("Font color ");
		
		JLabel lblFontSize = new JLabel("Font size");
		
		JLabel lblFontType = new JLabel("Font type");
		
		JComboBox comboFontType = new JComboBox();
		
		comboFontType.addItem("MonoSpaced");
		comboFontType.addItem("Verdana");
		comboFontType.addItem("Tahoma");
		comboFontType.addItem("Serif");
		
		comboFontType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = ((JComboBox)e.getSource()).getSelectedIndex();
				Font f = StoryTextArea.getFont();
				int fSize = f.getSize();
				switch(index) {
					case 0: Font font = new Font("MonoSpaced", Font.BOLD, fSize);
							StoryTextArea.setFont(font);
							break;
					case 1: Font font1 = new Font("Verdana", Font.BOLD, fSize);
							StoryTextArea.setFont(font1);
							break;
					case 2: Font font2 = new Font("Tahoma", Font.BOLD, fSize);
							StoryTextArea.setFont(font2);
							break;
					case 3: Font font3 = new Font("Serif", Font.BOLD, fSize);
							StoryTextArea.setFont(font3);
							break;
				}
			}
		});
		
		//layout for all of it.
		GroupLayout groupLayout = new GroupLayout(frmTextrpg.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblFontType)
						.addComponent(lblFontSize)
						.addComponent(lblFontColor)
						.addComponent(comboFontSize, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboFontType, 0, 103, Short.MAX_VALUE)
						.addComponent(comboFontColor, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(27)
					.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblFontColor)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboFontColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(lblFontSize)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboFontSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblFontType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboFontType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		/*panel variables are set up
		 * Originally initialized the button text.
		 * kept as is for readability later on.
		 */
		
		lbCharacterStats = new JLabel("Character stats");
		
		btnOption1 = new JButton("Option1");
		/*
		 * Area for button pressed actions
		 */
		btnOption1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = ((JButton)e.getSource()).getText();
				if(text != "") {
					//health = 0 minimum
					if(health <= 5) {
						health = 0;
						tagName = "deadend";
					} else {
						health -= 5;
						monsters += 1;
						tagName = op1Next;
						if(monsters == 3)
							tagName = "end";
					}				
					changeStoryText();
					changeCharacterStats();
				}else {
					((JButton)e.getSource()).setText("Restart");
					tagName = "chapter";
				}
				
			}
		});
		
		btnOption2 = new JButton("Option2");
		
		btnOption2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = ((JButton)e.getSource()).getText();
				if(text != "") {
					//health = 0 minimum
					if(health <= 3) {
						health = 0;
						tagName = "deadend";
					} else {
						health -= 3;
						tagName = op2Next;
					}
					changeStoryText();
					changeCharacterStats();
				}
				
			}
		});
		
		btnOption3 = new JButton("Option3");
		
		btnOption3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = ((JButton)e.getSource()).getText();
				if(text != "") {
					//health = 20 max
					if(health > 16) {
						health = health + (20-health);
					} else {
						health += 5;
					}
					tagName = op3Next;
					changeStoryText();
					changeCharacterStats();
				}
			
			}
		});
		
		btnOption4 = new JButton("Option4");
		
		btnOption4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = ((JButton)e.getSource()).getText();
				if(text != "") {
					//no need to change health.
					tagName = op4Next;
					changeStoryText();
				}
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		//no text to make "invisible"
		//btnNewButton.setOpaque(false);
		//btnNewButton.setContentAreaFilled(false);
		//btnNewButton.setBorderPainted(false);
		
		//another layout initializing
		GroupLayout gl_gamePanel = new GroupLayout(gamePanel);
		gl_gamePanel.setHorizontalGroup(
			gl_gamePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_gamePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_gamePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(btnOption4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(btnOption3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(btnOption2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
						.addComponent(lbCharacterStats, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOption1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_gamePanel.setVerticalGroup(
			gl_gamePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gamePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbCharacterStats, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnOption1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOption2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOption3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOption4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		StoryTextArea = new JTextArea();
		scrollPane.setViewportView(StoryTextArea);
		StoryTextArea.setBackground(new Color(211, 211, 211));
		gamePanel.setLayout(gl_gamePanel);
		frmTextrpg.getContentPane().setLayout(groupLayout);
	}
}//end of file

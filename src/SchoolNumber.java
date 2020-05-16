import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
public class SchoolNumber extends JFrame{
	JFrame f = new JFrame("学生学籍管理系统");
	JFrame ch;
	Container c = getContentPane();
	Boolean mbt;
	JTextField ID, sn, na, ci, bi, pr, no, gr, nam;
	JTextField ge;
	JLabel title;
	JPasswordField password;
	JLabel id, p, t;
	JPanel menuPanel, mainPanel;
	JButton mcheckbtn, Add, Search, Change, Delete;
	JMenuBar mb;
	JMenu m, help;
	JMenuItem add, search, change, delete;
	Font font = new Font("Default", Font.PLAIN, 32);
	private class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
            JOptionPane.showConfirmDialog (null, "你要添加此用户吗？","警告", dialogButton);
            if(dialogButton == JOptionPane.YES_OPTION) {
				Student.setID(Integer.parseInt(sn.getText()));
        		Student.setName(nam.getText());
        		Student.setBirthday(bi.getText());
        		Student.setGender(ci.getText());
        		Student.setCity(ci.getText());
        		Student.setProfession(pr.getText());
        		Student.setGrade(Integer.parseInt(no.getText()));
        		Student.setNote(gr.getText());
        		if(Utility.detectUser(Student.getName())==false) {
					Utility.AddUser();
        		}
        		else {
					JOptionPane d = new JOptionPane();
					d.showMessageDialog(null, "用户已存在！");
        		}
            if(dialogButton == JOptionPane.NO_OPTION) {
            	remove(dialogButton);
             	}
            }
		}
	};
	private class ChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
            JOptionPane.showConfirmDialog (null, "你要修改"+a+"吗？","警告", dialogButton);
            if(dialogButton == JOptionPane.YES_OPTION) {
            	Student.setID(Integer.parseInt(sn.getText()));
        		Student.setName(nam.getText());
        		Student.setBirthday(bi.getText());
        		Student.setGender(ge.getText());
        		Student.setCity(ci.getText());
        		Student.setProfession(pr.getText());
        		Student.setGrade(Integer.parseInt(no.getText()));
        		Student.setNote(gr.getText());
        		Utility.UpdateUser(a);
            if(dialogButton == JOptionPane.NO_OPTION) {
            	remove(dialogButton);
                }
              }
		}
	};
	private class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JMenuItem exit = (JMenuItem)e.getSource();
			System.exit(NORMAL);
		}
	};
	public JTextField STAddPanel(JPanel panel, String label, String value) {
		JPanel row = new JPanel();
		row.add(new JLabel(label));
		JTextField input = new JTextField(value, 10);
		row.add(input);
		panel.add(row);
		return input;
	}
	private class AddPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CreateAddPanel();
		}
	};
	private class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(Utility.searchUser(na.getText())==true) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
	            JOptionPane.showConfirmDialog (null, "你要搜索"+na.getText()+"吗？","警告", dialogButton);
	            if(dialogButton == JOptionPane.YES_OPTION) {
	            	CreateChangePanel(na.getText());
	            if(dialogButton == JOptionPane.NO_OPTION) {
	            	remove(dialogButton);
	                }
	            }
			}
			else {
				JOptionPane d =new JOptionPane();
				d.showMessageDialog(null, "请输入正确的姓名！");
			}
		}
	};
	public JPasswordField PSAddPanel(JPanel panel, String label, String value) {
		JPanel row = new JPanel();
		row.add(new JLabel(label));
		JPasswordField input = new JPasswordField(value, 10);
		row.add(input);
		panel.add(row);
		return input;
	}
	private class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
            JOptionPane.showConfirmDialog (null, "你要返回吗？未保存的数据将会消失。","警告", dialogButton);
            if(dialogButton == JOptionPane.YES_OPTION) {
            	CreateMainPanel();
            if(dialogButton == JOptionPane.NO_OPTION) {
            	remove(dialogButton);
		      	}
            }
		}
	};
	ActionListener deleteListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(Utility.searchUser(na.getText())==true) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
	            JOptionPane.showConfirmDialog (null, "你要删除"+na.getText()+"吗？","WARNING", dialogButton);
	            if(dialogButton == JOptionPane.YES_OPTION) {
	            	Utility.deleteUser(na.getText());
	            	JOptionPane d = new JOptionPane();
					d.showMessageDialog(null, "数据已删除！");
	            if(dialogButton == JOptionPane.NO_OPTION) {
	            	remove(dialogButton);
	                }
	              }
			}
			else {
				JOptionPane d =new JOptionPane();
				d.showMessageDialog(null, "请输入正确的姓名！");
			}
		}
	};
public JComboBox CBAddPanel(JPanel panel, String label) {
		JPanel row =new JPanel();
		row.add(new JLabel(label));
		JComboBox input = new JComboBox<String>();
		input.addItem("男");
		input.addItem("女");
		input.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		row.add(input);
		panel.add(row);
		return input;
	}
public JLabel LBAddPanel(JPanel panel, String title, Font font) {
		JPanel row = new JPanel();
		JLabel label = new JLabel(title);
		label.setFont(font);
		row.add(label);
		panel.add(row);
		return label;
	}
	//生成每个JPanel中的JTextField//
public void CreateMainPanel() {
	JPanel mainPanel = new JPanel();
	c.removeAll();
	c.add(mainPanel);
	JButton changebtn = new JButton("修改数据");
		changebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JPanel sp = new JPanel();
				sp.setVisible(true);
				c.removeAll();
				c.add(sp);
				JLabel n = new JLabel("姓名： ");
				JTextField na = new JTextField("", 10);
				JButton se = new JButton("搜索");
				se.addActionListener(new SearchListener());
		JButton addbtn = new JButton("添加数据");
		addbtn.addActionListener(new AddPanelListener());
		JButton searchbtn = new JButton("搜索数据");
		searchbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JPanel sp = new JPanel();
				sp.setVisible(true);
				c.removeAll();
				c.add(sp);
				JLabel n = new JLabel("姓名： ");
				JTextField na = new JTextField("", 10);
				JButton se = new JButton("搜索");
				se.addActionListener(new SearchListener());
				sp.add(n);
				sp.add(na);
				sp.add(se);
				f.revalidate();
				f.repaint();
			}
		});
		JButton deletebtn = new JButton("删除数据");
		deletebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JPanel dp = new JPanel();
				dp.setVisible(true);
				c.removeAll();
				c.add(dp);
				JLabel n = new JLabel("姓名： ");
				JTextField na = new JTextField("", 10);
				JButton de = new JButton("搜索");
				de.addActionListener(deleteListener);
				dp.add(n);
				dp.add(na);
				dp.add(de);
				f.revalidate();
				f.repaint();
			}
		});
		mainPanel.add(changebtn);
		mainPanel.add(addbtn);
		mainPanel.add(searchbtn);
		mainPanel.add(deletebtn);
		f.revalidate();
		f.repaint();
	}
}
	public void CreateChangePanel(String a) {
		Utility.GetUser(a);
		JPanel change = new JPanel();
		change.setVisible(true);
		c.removeAll();
		JButton changebtn = new JButton("修改数据");
		changebtn.addActionListener(new ChangeListener());
		JButton back = new JButton("返回");
		back.addActionListener(new BackListener());
		sn = STAddPanel(change, "学号： ", ""+Student.getID());
		nam = STAddPanel(change, "姓名： ",Student.getName());
		ge = STAddPanel(change, "性别：  ",Student.getGender());
		ci = STAddPanel(change, "城市： ",Student.getCity());
		bi = STAddPanel(change, "出生日期：",""+Student.getBirthday());
		pr = STAddPanel(change, "专业:", Student.getProfession());
		no = STAddPanel(change, "年级： ", ""+Student.getGrade());
		gr = STAddPanel(change, "备注： ", Student.getNote() );
		change.add(changebtn);
		change.add(back);
		c.add(change);
		change.setLayout(new GridLayout(9,1,2,2));
		f.revalidate();
		f.repaint();
	}
	//生成修改数据的面板//
	public void CreateAddPanel() {
		JPanel change = new JPanel();
		change.setVisible(true);
		c.removeAll();
		JButton addbtn = new JButton("添加数据");
		addbtn.addActionListener(new AddListener());
		JButton back = new JButton("返回");
		back.addActionListener(new BackListener());
		sn = STAddPanel(change, "学号： ", "");
		nam = STAddPanel(change, "姓名： ", "");
		ge = STAddPanel(change, "性别：  ", "");
		ci = STAddPanel(change, "城市：  ","");
		bi = STAddPanel(change, "出生日期：","");
		pr = STAddPanel(change, "专业:", "");
		no = STAddPanel(change, "年级： ", "");
		gr = STAddPanel(change, "备注： ", "");
		change.add(addbtn);
		change.add(back);
		c.add(change);
		change.setLayout(new GridLayout(9,1,2,2));
		f.revalidate();
		f.repaint();
		}
	//面板代码//
	public SchoolNumber() {
		f.setSize(960, 720);
		f.setVisible(true);
		c.setVisible(true);
		Boolean mbt = false;
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(0, 2));
		String t = "学生学籍管理系统";
		JButton mcheckbtn = new JButton("检查系统");
		mcheckbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Utility.userExist(ID.getText(), password.getText())==true) {
					CreateMainPanel();
				}
				else{
					JOptionPane d = new JOptionPane();
					d.showMessageDialog(null, "请输入正确的用户名和密码！");
				}
			}
		});
		JMenuBar mb = new JMenuBar();
		JMenuItem add = new JMenuItem("添加数据");
		JMenuItem search = new JMenuItem("搜索数据");
		JMenuItem delete = new JMenuItem("删除数据");
		JMenuItem change = new JMenuItem("修改数据");
		JMenuItem exit = new JMenuItem("退出");
		f.setJMenuBar(mb);
		JMenu m = new JMenu("文件");
		JMenu help = new JMenu("帮助");
		m.setVisible(true);
		m.add(search);
		m.add(add);
		m.add(change);
		m.add(delete);
		m.add(exit);
		exit.addActionListener(new ExitListener());
		search.setEnabled(mbt);
		add.setEnabled(mbt);
		change.setEnabled(mbt);
		delete.setEnabled(mbt);
		mb.add(m);
		mb.add(help);
		title = LBAddPanel(menuPanel, t, font);
		ID = STAddPanel(menuPanel, "用户名： ", "");
		password = PSAddPanel(menuPanel, "密码： ", "");
		menuPanel.add(mcheckbtn);
		menuPanel.setVisible(true);
		menuPanel.setLayout(new GridLayout(5, 2, 1, 1));
		c.add(menuPanel);
		f.add(c);
		f.revalidate();
		f.repaint();
	}
	public static void main(String[] args) {
		new SchoolNumber();
	}
}
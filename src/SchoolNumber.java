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
	GridBagLayout gb =new GridBagLayout();
	JButton check;
	Boolean mbt;
	JTextField ID, sn, na, ge, ci, bi, pr, no, gr;
	JPasswordField Password;
	JLabel id, p, t;
	JPanel MenuPanel, MainPanel, ChangePanel;
	JButton Check, Add, Search, Change, Delete;
	JMenuBar mb;
	JMenu m, help;
	JMenuItem add, search, change, delete;
	Font font = new Font("Default", Font.PLAIN, 32);
	public void layout(Component c, GridBagConstraints constraints, GridBagLayout gb) {
		gb.setConstraints(c, constraints);
        add(c);
	}
	public SchoolNumber() {
		f.setSize(960, 720);
		f.setVisible(true);
		c.setLayout(gb);
		c.setVisible(true);
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
		Boolean mbt = false;
		JPanel MenuPanel = new JPanel();
		JLabel t = new JLabel("学生学籍管理系统");
		t.setFont(font);
		t.setVisible(true);
		layout(t, constraints, gb);
		JTextField ID = new JTextField("", 10);
		ID.setVisible(true);
		JPasswordField Password = new JPasswordField("", 10);
		Password.setVisible(true);
		JLabel id = new JLabel("用户名: ");
		id.setVisible(true);
		JLabel p = new JLabel("密码: ");
		p.setVisible(true);
		JButton Check = new JButton("检查系统");
		Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Utility.userExist(ID.getText(), Password.getText())) {
					JPanel MainPanel = new JPanel();
					c.remove(MenuPanel);
					c.add(MainPanel);
					JButton Change = new JButton("修改数据");
					Change.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							JPanel Change = new JPanel();
							Change.setVisible(true);
							c.removeAll();
							JLabel a = new JLabel("学号:");
							JLabel b = new JLabel("姓名: ");
							JLabel c1 = new JLabel("性别: ");
							JLabel d = new JLabel("籍贯: ");
							JLabel e1 = new JLabel("专业: ");
							JLabel f1 = new JLabel("出生日期： ");
							JLabel g = new JLabel("备注：  ");
							JLabel h = new JLabel("年级： ");
							JTextField sn = new JTextField("", 10);
							JTextField na = new JTextField("", 10);
							JTextField ge = new JTextField("", 10);
							JTextField ci = new JTextField("", 10);
							JTextField bi = new JTextField("", 10);
							JTextField pr = new JTextField("", 10);
							JTextField no = new JTextField("", 10);
							JTextField gr = new JTextField("", 10);
							Change.add(a);
							Change.add(sn);
							Change.add(b);
							Change.add(na);
							Change.add(c1);
							Change.add(ge);
							Change.add(d);
							Change.add(ci);
							Change.add(e1);
							Change.add(bi);
							Change.add(f1);
							Change.add(pr);
							Change.add(g);
							Change.add(no);
							Change.add(h);
							Change.add(gr);
							c.add(Change);
							f.revalidate();
							f.repaint();
						}
					});
					JButton Add = new JButton("添加数据");
					Add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JPanel Add = new JPanel();
							Add.setVisible(true);
							c.removeAll();
							JLabel a = new JLabel("学号:");
							JLabel b = new JLabel("姓名: ");
							JLabel c1 = new JLabel("性别: ");
							JLabel d = new JLabel("籍贯: ");
							JLabel e1 = new JLabel("专业: ");
							JLabel f1 = new JLabel("出生日期： ");
							JLabel g = new JLabel("备注：  ");
							JLabel h = new JLabel("年级： ");
							JTextField sn = new JTextField("", 10);
							JTextField na = new JTextField("", 10);
							JTextField ge = new JTextField("", 10);
							JTextField ci = new JTextField("", 10);
							JTextField bi = new JTextField("", 10);
							JTextField pr = new JTextField("", 10);
							JTextField no = new JTextField("", 10);
							JTextField gr = new JTextField("", 10);
							Add.add(a);
							Add.add(sn);
							Add.add(b);
							Add.add(na);
							Add.add(c1);
							Add.add(ge);
							Add.add(d);
							Add.add(ci);
							Add.add(e1);
							Add.add(bi);
							Add.add(f1);
							Add.add(pr);
							Add.add(g);
							Add.add(no);
							Add.add(h);
							Add.add(gr);
							c.add(Add);
							f.revalidate();
							f.repaint();
						}
					});
					JButton Search = new JButton("搜索数据");
					Search.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							JPanel sp = new JPanel();
							sp.setVisible(true);
							c.removeAll();
							c.add(sp);
							JLabel n = new JLabel("姓名： ");
							JTextField na = new JTextField("", 10);
							JButton se = new JButton("搜索");
							se.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									if(Utility.searchUser(na.getText())==true) {
										int dialogButton = JOptionPane.YES_NO_OPTION;
							            JOptionPane.showConfirmDialog (null, "你要搜索"+na.getText()+"吗？","WARNING", dialogButton);
							            if(dialogButton == JOptionPane.YES_OPTION) {
							            	
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
							});
							sp.add(n);
							sp.add(na);
							sp.add(se);
							f.revalidate();
							f.repaint();
						}
					});
					JButton Delete = new JButton("删除数据");
					Delete.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							JPanel dp = new JPanel();
							dp.setVisible(true);
							c.removeAll();
							c.add(dp);
							JLabel n = new JLabel("姓名： ");
							JTextField na = new JTextField("", 10);
							JButton de = new JButton("搜索");
							de.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									if(Utility.searchUser(na.getText())==true) {
										int dialogButton = JOptionPane.YES_NO_OPTION;
							            JOptionPane.showConfirmDialog (null, "你要删除"+na.getText()+"吗？","WARNING", dialogButton);
							            if(dialogButton == JOptionPane.YES_OPTION) {
							            	Utility.deleteUser(na.getText());
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
							});
							dp.add(n);
							dp.add(na);
							dp.add(de);
							f.revalidate();
							f.repaint();
						}
					});
					MainPanel.add(Change);
					MainPanel.add(Add);
					MainPanel.add(Search);
					MainPanel.add(Delete);
					f.revalidate();
					f.repaint();
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
		class ExitListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				JMenuItem exit = (JMenuItem)e.getSource();
				System.exit(NORMAL);
			}
		}
		exit.addActionListener(new ExitListener());
		if(Utility.userExist(ID.getText(), Password.getText())) {
			mbt = true;
			repaint();
		}
		search.setEnabled(mbt);
		add.setEnabled(mbt);
		change.setEnabled(mbt);
		delete.setEnabled(mbt);
		mb.add(m);
		mb.add(help);
		MenuPanel.add(t, constraints);
		MenuPanel.add(id);
		MenuPanel.add(ID);
		MenuPanel.add(p);
		MenuPanel.add(Password);
		MenuPanel.add(Check);
		MenuPanel.setVisible(true);
		c.add(MenuPanel);
		f.add(c);
		f.revalidate();
		f.repaint();
	}
	public static void main(String[] args) {
		new SchoolNumber();
	}
}

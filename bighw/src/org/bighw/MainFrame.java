package org.bighw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	private JButton[][] jbs = new JButton[10][10];// 按钮组
	private JLabel jl_tip = new JLabel("提示：");
	private JTextField jt_tip = new JTextField(40);
	private JButton jb_NO_OFF = new JButton("布置完成");
	private JButton jb_next = new JButton("下一周期");
	private JButton jb_evolution = new JButton("开始演化");
	private JButton jb_pause = new JButton("暂停");
	private JPanel jp_north = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel jp_center = new JPanel(new GridLayout(10, 10, 1, 1));  // 细胞皿面板
	private JPanel jp_east = new JPanel(new GridLayout(2, 2));
	private JPanel jp_east1 = new JPanel(new GridLayout(5, 2));
	private JPanel jp_east2 = new JPanel(new GridLayout(4, 1, 0, 10));//GUI面板嵌套
	private boolean isEliminate = false;// 判断是否是淘汰周期
	
	public Time time = new Time();
	public Logic logic = new Logic();

	private static final long serialVersionUID = 1L;//定义程序序列化ID。，序列化ID等同于身份验证，主要用于程序的版本控制，维护不同版本的兼容性以及避免在程序版本升级时程序报告的错误

	public int getjbslength(){
		return jbs.length;
	}
	public int getjbs0length(){
		return jbs[0].length;
	}
	public JButton[][] getjbs(){
		return jbs;
	}
	/**
	 * 生命游戏主框架
	 */
	public MainFrame() {
		
		setTitle("生命游戏");
		setLayout(new BorderLayout());//边框
		setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭操作
		setBounds(0, 0, 1000, 700);//边界值
		setLocationRelativeTo(null);
		setResizable(false);

		initEast();//初始化右侧菜单
		this.initButton();//初始化界面,添加按钮

		jl_tip.setFont(new Font("黑体", Font.BOLD, 20));
		jt_tip.setFont(new Font("黑体", Font.PLAIN, 18));
		jt_tip.setEditable(false);
		jt_tip.setText("点击下面的格子以放置细胞！");

		jp_north.add(jl_tip, BorderLayout.NORTH);
		jp_north.add(jt_tip, BorderLayout.NORTH);

		add(jp_north, BorderLayout.NORTH);
		add(jp_center, BorderLayout.CENTER);
		add(jp_east, BorderLayout.EAST);//边框布局

		setVisible(true);
	}
	
	/**
	 * 初始化界面，添加按钮，并为按钮添加监听
	 */
	public void initButton() {
		for (int i = 0; i < jbs.length; i++)
			for (int j = 0; j < jbs[0].length; j++) {
				jbs[i][j] = new JButton();
				jbs[i][j].setBorder(BorderFactory.createLineBorder(Color.gray));// 设置边框
				jbs[i][j].setBackground(Color.white);// 设置背景颜色
				jbs[i][j].setForeground(Color.blue);// 设置字体颜色
				jbs[i][j].setFont(new Font("迷你简菱心", Font.BOLD, 30));// 设置字体
				jbs[i][j].addActionListener(time.al);
				jp_center.add(jbs[i][j]);
			}
		jb_next.setEnabled(false);//设置下一周期不可用
		jb_pause.setEnabled(false);//设置暂停按钮不可用
		jb_evolution.setEnabled(false);
		jp_center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}
	
	/**
	 * 初始化右侧菜单面板
	 */
	public void initEast() {
		Font font1 = new Font("迷你简菱心", Font.BOLD, 20);
		Font font2 = new Font("黑体", Font.PLAIN, 20);
		JLabel jl_alive = new JLabel("@", JLabel.CENTER);
		JLabel jl_willLive = new JLabel("#", JLabel.CENTER);
		JLabel jl_willDie = new JLabel("$", JLabel.CENTER);
		JLabel tip1 = new JLabel("存活细胞");
		JLabel tip2 = new JLabel("即将复活的细胞");
		JLabel tip3 = new JLabel("即将死亡的细胞");
		
		jl_alive.setFont(font1);
		tip1.setFont(font2);
		jl_alive.setForeground(Color.blue);
		jl_willLive.setFont(font1);
		tip2.setFont(font2);
		jl_willLive.setForeground(Color.GREEN);
		jl_willDie.setFont(font1);
		tip3.setFont(font2);
		jl_willDie.setForeground(Color.red);

		jb_NO_OFF.setFont(font2);
		jb_next.setFont(font2);
		jb_evolution.setFont(font2);
		jb_pause.setFont(font2);

		jp_east1.add(jl_alive);
		jp_east1.add(tip1);
		jp_east1.add(jl_willLive);
		jp_east1.add(tip2);
		jp_east1.add(jl_willDie);
		jp_east1.add(tip3);
		jp_east2.add(jb_NO_OFF);
		jp_east2.add(jb_next);
		jp_east2.add(jb_evolution);
		jp_east2.add(jb_pause);
		
		//传参
		time.setjbslength(jbs.length);
		time.setjbs0length(jbs[0].length);
		time.setlogic(logic);
		logic.settime(time);
		logic.setjbs(jbs,jb_next,jb_evolution);

		//监听
		//布置完成或重新布置
		jb_NO_OFF.addActionListener(e -> {
			if (((AbstractButton) e.getSource()).getText().equals("布置完成")) {
				logic.setDisClickable();
				jb_next.setEnabled(true);
				jt_tip.setText("点击\"下一周期\"进行生命周期演化！");
				((AbstractButton) e.getSource()).setText("重新布置");
			} else {		//重新布置
				jp_center.removeAll();//移除原来的所有按钮
				initButton();//重新初始化按钮
				isEliminate = false;
				time.setisEliminate(isEliminate);
				jb_next.setEnabled(false);
				if(time.updateTimer.isRunning()) time.updateTimer.stop();//如果正在细胞周期，则停止更新
				jt_tip.setText("点击下面的格子以放置细胞！");
				((AbstractButton) e.getSource()).setText("布置完成");
			}
		});
		//下一周期
		jb_next.addActionListener(e -> {
			time.nextTerm(jbs.length,jbs[0].length);		
		});
		//周期演化
		jb_evolution.addActionListener(e->{
			time.updateTimer.start();	
			((AbstractButton)e.getSource()).setEnabled(false);
			jb_pause.setEnabled(true);
			jb_next.setEnabled(false);
		});
		//停止演化
		jb_pause.addActionListener(e->{
			time.updateTimer.stop();		
			((AbstractButton)e.getSource()).setEnabled(false);
			jb_evolution.setEnabled(true);
			jb_next.setEnabled(true);
		});

		jp_east.add(jp_east1);
		jp_east.add(jp_east2);//嵌套新面板
		
		jp_east1.setBorder(BorderFactory.createEmptyBorder(0, -30, 0, 50));
		jp_east2.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));//创建空面板
	}
	/**
	 * 构建生命游戏
	 */
	public static void main(String[] args) {
		new MainFrame();
    }

}

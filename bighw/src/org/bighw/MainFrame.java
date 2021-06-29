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
	private JButton[][] jbs = new JButton[10][10];// ��ť��
	private JLabel jl_tip = new JLabel("��ʾ��");
	private JTextField jt_tip = new JTextField(40);
	private JButton jb_NO_OFF = new JButton("�������");
	private JButton jb_next = new JButton("��һ����");
	private JButton jb_evolution = new JButton("��ʼ�ݻ�");
	private JButton jb_pause = new JButton("��ͣ");
	private JPanel jp_north = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel jp_center = new JPanel(new GridLayout(10, 10, 1, 1));  // ϸ�������
	private JPanel jp_east = new JPanel(new GridLayout(2, 2));
	private JPanel jp_east1 = new JPanel(new GridLayout(5, 2));
	private JPanel jp_east2 = new JPanel(new GridLayout(4, 1, 0, 10));//GUI���Ƕ��
	private boolean isEliminate = false;// �ж��Ƿ�����̭����
	
	public Time time = new Time();
	public Logic logic = new Logic();

	private static final long serialVersionUID = 1L;//����������л�ID�������л�ID��ͬ�������֤����Ҫ���ڳ���İ汾���ƣ�ά����ͬ�汾�ļ������Լ������ڳ���汾����ʱ���򱨸�Ĵ���

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
	 * ������Ϸ�����
	 */
	public MainFrame() {
		
		setTitle("������Ϸ");
		setLayout(new BorderLayout());//�߿�
		setDefaultCloseOperation(EXIT_ON_CLOSE);//�رղ���
		setBounds(0, 0, 1000, 700);//�߽�ֵ
		setLocationRelativeTo(null);
		setResizable(false);

		initEast();//��ʼ���Ҳ�˵�
		this.initButton();//��ʼ������,��Ӱ�ť

		jl_tip.setFont(new Font("����", Font.BOLD, 20));
		jt_tip.setFont(new Font("����", Font.PLAIN, 18));
		jt_tip.setEditable(false);
		jt_tip.setText("�������ĸ����Է���ϸ����");

		jp_north.add(jl_tip, BorderLayout.NORTH);
		jp_north.add(jt_tip, BorderLayout.NORTH);

		add(jp_north, BorderLayout.NORTH);
		add(jp_center, BorderLayout.CENTER);
		add(jp_east, BorderLayout.EAST);//�߿򲼾�

		setVisible(true);
	}
	
	/**
	 * ��ʼ�����棬��Ӱ�ť����Ϊ��ť��Ӽ���
	 */
	public void initButton() {
		for (int i = 0; i < jbs.length; i++)
			for (int j = 0; j < jbs[0].length; j++) {
				jbs[i][j] = new JButton();
				jbs[i][j].setBorder(BorderFactory.createLineBorder(Color.gray));// ���ñ߿�
				jbs[i][j].setBackground(Color.white);// ���ñ�����ɫ
				jbs[i][j].setForeground(Color.blue);// ����������ɫ
				jbs[i][j].setFont(new Font("���������", Font.BOLD, 30));// ��������
				jbs[i][j].addActionListener(time.al);
				jp_center.add(jbs[i][j]);
			}
		jb_next.setEnabled(false);//������һ���ڲ�����
		jb_pause.setEnabled(false);//������ͣ��ť������
		jb_evolution.setEnabled(false);
		jp_center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}
	
	/**
	 * ��ʼ���Ҳ�˵����
	 */
	public void initEast() {
		Font font1 = new Font("���������", Font.BOLD, 20);
		Font font2 = new Font("����", Font.PLAIN, 20);
		JLabel jl_alive = new JLabel("@", JLabel.CENTER);
		JLabel jl_willLive = new JLabel("#", JLabel.CENTER);
		JLabel jl_willDie = new JLabel("$", JLabel.CENTER);
		JLabel tip1 = new JLabel("���ϸ��");
		JLabel tip2 = new JLabel("���������ϸ��");
		JLabel tip3 = new JLabel("����������ϸ��");
		
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
		
		//����
		time.setjbslength(jbs.length);
		time.setjbs0length(jbs[0].length);
		time.setlogic(logic);
		logic.settime(time);
		logic.setjbs(jbs,jb_next,jb_evolution);

		//����
		//������ɻ����²���
		jb_NO_OFF.addActionListener(e -> {
			if (((AbstractButton) e.getSource()).getText().equals("�������")) {
				logic.setDisClickable();
				jb_next.setEnabled(true);
				jt_tip.setText("���\"��һ����\"�������������ݻ���");
				((AbstractButton) e.getSource()).setText("���²���");
			} else {		//���²���
				jp_center.removeAll();//�Ƴ�ԭ�������а�ť
				initButton();//���³�ʼ����ť
				isEliminate = false;
				time.setisEliminate(isEliminate);
				jb_next.setEnabled(false);
				if(time.updateTimer.isRunning()) time.updateTimer.stop();//�������ϸ�����ڣ���ֹͣ����
				jt_tip.setText("�������ĸ����Է���ϸ����");
				((AbstractButton) e.getSource()).setText("�������");
			}
		});
		//��һ����
		jb_next.addActionListener(e -> {
			time.nextTerm(jbs.length,jbs[0].length);		
		});
		//�����ݻ�
		jb_evolution.addActionListener(e->{
			time.updateTimer.start();	
			((AbstractButton)e.getSource()).setEnabled(false);
			jb_pause.setEnabled(true);
			jb_next.setEnabled(false);
		});
		//ֹͣ�ݻ�
		jb_pause.addActionListener(e->{
			time.updateTimer.stop();		
			((AbstractButton)e.getSource()).setEnabled(false);
			jb_evolution.setEnabled(true);
			jb_next.setEnabled(true);
		});

		jp_east.add(jp_east1);
		jp_east.add(jp_east2);//Ƕ�������
		
		jp_east1.setBorder(BorderFactory.createEmptyBorder(0, -30, 0, 50));
		jp_east2.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));//���������
	}
	/**
	 * ����������Ϸ
	 */
	public static void main(String[] args) {
		new MainFrame();
    }

}

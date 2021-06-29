package org.bighw;

import java.awt.Color;
import javax.swing.JButton;


public class Logic {
	private JButton[][] jbs;
	private JButton jb_next;
	private JButton jb_evolution;
	private Time time;

	public void setjbs(JButton[][] jbs,JButton jb_next,JButton jb_evolution){
		this.jbs = jbs;
		this.jb_next = jb_next;
		this.jb_evolution = jb_evolution;
	}
	
	public void settime(Time time){
		this.time = time;
	}

	/**
	 * ��̭����->ĩ̬
	 * ����ϸ������������ϸ�����Ƴ�����ϸ��
	 */
	public void add_remove(int i,int j) {
		if(jbs[i][j].getText().equals("#")) {
			jbs[i][j].setText("@");
			jbs[i][j].setForeground(Color.blue);
		}else if(jbs[i][j].getText().equals("$")) {
			jbs[i][j].setText("");
		}else {
			return;
		}
	}

	/**
	 * ��̬->��̭����
	 * ����ĳ��λ�õ�״̬
	 */
	public void setState(int i, int j) {
		switch (countSurround(i, j)) {
		case 0:
		case 1:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			if(jbs[i][j].getText().equals("@")) {
				jbs[i][j].setText("$");jbs[i][j].setForeground(Color.RED);
			}
			break;
		case 2:
			if(jbs[i][j].getText().equals("")) {
				jbs[i][j].setText("#");jbs[i][j].setForeground(Color.GREEN);
			}
			break;
		default:
			break;
		}
	}
//	public void setState(int i, int j) {
//		if(countSurround(i, j)>=0&&countSurround(i, j)<=8&&countSurround(i, j)!=2||countSurround(i, j)!=3)
//			if(jbs[i][j].getText().equals("@")) {
//				jbs[i][j].setText("$");jbs[i][j].setForeground(Color.RED);
//			}
//		if(countSurround(i, j)==3){
//			if(jbs[i][j].getText().equals("")) {
//				jbs[i][j].setText("#");jbs[i][j].setForeground(Color.GREEN);
//			}
//		}
//	}

	/**
	 * �����Ӧλ����Χ��ϸ��������return��int���͵�ϸ������
	 */
	public int countSurround(int i, int j) {
		return count(i - 1, j - 1) + count(i - 1, j) + count(i - 1, j + 1) + count(i, j - 1) + count(i, j + 1)
					+ count(i + 1, j - 1) + count(i + 1, j) + count(i + 1, j + 1);
	}
	/**
	 * ����ĳ��λ���Ƿ���ڻ�ϸ��������з���1��û�з���0
	 */
	
	public int count(int i, int j) {
		if(i<0||i>=jbs.length||j<0||j>=jbs[0].length)//����±곬���߽磬���Ϊ0
			return 0;
		if (jbs[i][j].getText().equals("@")||jbs[i][j].getText().equals("$"))
			return 1;
		else
			return 0;
	}
	
	/**
	 * ��������ϸ�������²���
	 */
	public void setDisClickable() {
		for (int i = 0; i < jbs.length; i++)
			for (int j = 0; j < jbs[0].length; j++) {
				jbs[i][j].removeActionListener(time.al);
			}
		jb_next.setEnabled(true);//������һ���ڰ�ť
		jb_evolution.setEnabled(true);
	}
}

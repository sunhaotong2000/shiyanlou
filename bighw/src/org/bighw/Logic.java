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
	 * 淘汰周期->末态
	 * 更新细胞皿，新增复活细胞，移除死亡细胞
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
	 * 初态->淘汰周期
	 * 设置某个位置的状态
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
	 * 计算对应位置周围的细胞数量，return是int类型的细胞数量
	 */
	public int countSurround(int i, int j) {
		return count(i - 1, j - 1) + count(i - 1, j) + count(i - 1, j + 1) + count(i, j - 1) + count(i, j + 1)
					+ count(i + 1, j - 1) + count(i + 1, j) + count(i + 1, j + 1);
	}
	/**
	 * 计算某个位置是否存在活细胞，如果有返回1，没有返回0
	 */
	
	public int count(int i, int j) {
		if(i<0||i>=jbs.length||j<0||j>=jbs[0].length)//如果下标超出边界，则记为0
			return 0;
		if (jbs[i][j].getText().equals("@")||jbs[i][j].getText().equals("$"))
			return 1;
		else
			return 0;
	}
	
	/**
	 * 禁用所有细胞皿，重新布置
	 */
	public void setDisClickable() {
		for (int i = 0; i < jbs.length; i++)
			for (int j = 0; j < jbs[0].length; j++) {
				jbs[i][j].removeActionListener(time.al);
			}
		jb_next.setEnabled(true);//启用下一周期按钮
		jb_evolution.setEnabled(true);
	}
}

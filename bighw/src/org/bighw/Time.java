package org.bighw;

import java.awt.Color;

import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.Timer;

public class Time{
	private boolean isEliminate;//淘汰周期
	private int jbslength;
	private int jbs0length;
	private Logic logic;

	
	public void setjbslength(int jbslength){
		this.jbslength = jbslength;
	}
	
	public void setjbs0length(int jbs0length){
		this.jbs0length = jbs0length;
	}
	
	public void setlogic(Logic logic){
		this.logic = logic;
	}
	
	public void setisEliminate(boolean isEliminate){
		this.isEliminate = isEliminate;
	}
	
	/**
	 * 细胞皿细胞状态监听,监听器接口
	 */
	ActionListener al = e -> {
		if (((AbstractButton) e.getSource()).getText().equals("@")) {
			((AbstractButton) e.getSource()).setText("");
		} else {
			((AbstractButton) e.getSource()).setText("@");
			((AbstractButton) e.getSource()).setForeground(Color.blue);
		}
	};
	
	/*
	 * 刷新的监听，用于延迟执行
	 */
	ActionListener updateing=e->{
		this.nextTerm(jbslength,jbs0length);
	};
	
	/**
	 * 时钟，延迟20毫秒执行
	 */
	Timer updateTimer=new Timer(10,updateing);
	
	
	/**
	 * 下一周期
	 */
	public void nextTerm(int jbslength ,int jbs0length) {
		if (!isEliminate) {//如果不是淘汰周期,就遍历,设置全屏幕的细胞状态
			for (int i = 0; i < jbslength; i++)
				for (int j = 0; j < jbs0length; j++) {
					logic.setState(i, j);
				}
			isEliminate=true;
		}else {//如果是淘汰周期,就遍历.更新细胞皿，新增复活细胞，移除死亡细胞
			for (int i = 0; i < jbslength; i++)
				for (int j = 0; j < jbs0length; j++) {

					logic.add_remove(i, j);
				}
			isEliminate=false;
		}
	}

}

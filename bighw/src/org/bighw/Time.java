package org.bighw;

import java.awt.Color;

import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.Timer;

public class Time{
	private boolean isEliminate;//��̭����
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
	 * ϸ����ϸ��״̬����,�������ӿ�
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
	 * ˢ�µļ����������ӳ�ִ��
	 */
	ActionListener updateing=e->{
		this.nextTerm(jbslength,jbs0length);
	};
	
	/**
	 * ʱ�ӣ��ӳ�20����ִ��
	 */
	Timer updateTimer=new Timer(10,updateing);
	
	
	/**
	 * ��һ����
	 */
	public void nextTerm(int jbslength ,int jbs0length) {
		if (!isEliminate) {//���������̭����,�ͱ���,����ȫ��Ļ��ϸ��״̬
			for (int i = 0; i < jbslength; i++)
				for (int j = 0; j < jbs0length; j++) {
					logic.setState(i, j);
				}
			isEliminate=true;
		}else {//�������̭����,�ͱ���.����ϸ������������ϸ�����Ƴ�����ϸ��
			for (int i = 0; i < jbslength; i++)
				for (int j = 0; j < jbs0length; j++) {

					logic.add_remove(i, j);
				}
			isEliminate=false;
		}
	}

}

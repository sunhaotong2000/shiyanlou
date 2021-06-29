package org.bighw;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	private static MainFrame mainframe = new MainFrame();
	
	@Before
	public void setUp() throws Exception {
		mainframe.logic.setDisClickable();//ÿ�ε�Ԫ����֮ǰ�����Ƚ���������Ϊ��ʼ״̬
	}
	@Test
	public void testAdd_remove() {
		mainframe.getjbs()[5][4].setText("#");//�ڵ�5�е�4�з���һ�����������ϸ��������Ϊ�о�����
		assertEquals(0,mainframe.logic.count(5,4));//��ʼ״̬�о�����δ���count����0
		mainframe.logic.add_remove(5,4);//����ϸ��״̬
		assertEquals(1,mainframe.logic.count(5,4));//�о����󸴻count����1
		
		mainframe.getjbs()[5][3].setText("$");//�ڵ�5�е�3�з���һ������������ϸ��������Ϊ�о�����
		assertEquals(1,mainframe.logic.count(5,3));//��ʼ״̬�о�����δ������count����1
		mainframe.logic.add_remove(5,3);//����ϸ��״̬
		assertEquals(0,mainframe.logic.count(5,3));//�о�����������count����0
		System.out.println("The method add_remove() runs successfully.");
	}
	@Test
	public void testSetState() {

		mainframe.getjbs()[5][4].setText("@");//�ڵ�5�е�4�з���һ����ϸ��������Ϊ�о�����
		mainframe.logic.setState(5,4);//����ϸ��״̬
		assertEquals(1,mainframe.logic.count(5,4));//�о�������Χû������ϸ�����о������ɼ���������ϸ����count����1
		mainframe.logic.setDisClickable();
		
		mainframe.getjbs()[5][4].setText("");//�ڵ�5�е�4�з���һ����ϸ��������Ϊ�о�����
		mainframe.getjbs()[5][3].setText("@");
		mainframe.getjbs()[6][3].setText("@");
		mainframe.getjbs()[6][4].setText("@");
		mainframe.logic.setState(5,4);//����ϸ��״̬
		assertEquals(0,mainframe.logic.count(5,4));//�о�������Χ��3��ϸ�����о������ɼ��������ϸ����count����0
		System.out.println("The method setState() runs successfully.");

	}
	@Test
	public void testCountSurround() {
		mainframe.getjbs()[0][0].setText("@");//�ڵ�1�е�8�з���һ��ϸ��
		mainframe.getjbs()[3][6].setText("@");//�ڵ�1�е�9�з���һ��ϸ��
		assertEquals(0,mainframe.logic.countSurround(0,0));//��1�е�9�е�ϸ����Χ��ϸ����Ϊ1��countSurround����1
		
		
		
//		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
//		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
//		assertEquals(4,mainframe.logic.countSurround(3,7));//��һ״̬��ϸ������Χϸ����̫�ٶ�������countSurround����0
//		System.out.println("The method countSurround() runs successfully.");
	}
	@Test
	public void testCount() {
		mainframe.getjbs()[5][4].setText("@");//�ڵ�5�е�4�з���һ����ϸ��
		mainframe.getjbs()[5][3].setText("$");//�ڵ�5�е�3�з���һ������������ϸ��
		mainframe.getjbs()[5][2].setText("");//�ڵ�5�е�2�з���һ����ϸ��
		mainframe.getjbs()[5][1].setText("#");//�ڵ�5�е�1�з���һ�����������ϸ��
		assertEquals(1,mainframe.logic.count(5,4));
		assertEquals(1,mainframe.logic.count(5,3));//��ϸ��Ϊ��ϸ�����߼���������ϸ����count����1
		assertEquals(0,mainframe.logic.count(5,2));
		assertEquals(0,mainframe.logic.count(5,1));//��ϸ��Ϊ��ϸ�����߼��������ϸ����count����0
		System.out.println("The method count() runs successfully.");
	}
	@Test
	public void testSetDisClickable() {
		mainframe.logic.setDisClickable();//����������Ϊ��ʼ״̬
		System.out.println("The method setDisClickable() runs successfully.");
	}
}

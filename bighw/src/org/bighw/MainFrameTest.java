package org.bighw;

import org.junit.Before;
import org.junit.Test;

public class MainFrameTest {
	private static MainFrame mainframe = new MainFrame();
	
	@Before
	public void setUp() throws Exception {
		mainframe.logic.setDisClickable();//ÿ�ε�Ԫ����֮ǰ�����Ƚ���������Ϊ��ʼ״̬
	}
	@Test
	public void testInitButton() {
		mainframe.initButton();//��ʼ�����棬��Ӱ�ť
		System.out.println("The method initButton() runs successfully.");//��������������ѭ�������������Զ���ᱻִ��
	}
	@Test
	public void testInitEast() {
        mainframe.initEast();//��ʼ���Ҳ�˵����
		System.out.println("The method initEast() runs successfully.");//��������������ѭ�������������Զ���ᱻִ��
	}
}

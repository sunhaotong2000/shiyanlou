package org.bighw;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TimeTest {

	private static MainFrame mainframe = new MainFrame();

	@Before
	public void setUp() throws Exception {
		mainframe.logic.setDisClickable();// ÿ�ε�Ԫ����֮ǰ�����Ƚ���������Ϊ��ʼ״̬
	}

	@Test
	public void testNextTerm() {
		// ��logic���У������һ�ε������һ���ڡ�ʱ����̭����״̬����עϸ���Ǽ����������Ǽ���������ڶ��ε������һ���ڡ�ʱ����ĩ̬
		// ��˱Ƚ�����״̬ʱ��Ҫ��������nextTerm()����

		mainframe.getjbs()[5][4].setText("@");// �ڵ�5�е�4�з���һ����ϸ��������Ϊ�о�����
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		assertEquals(0, mainframe.logic.count(5, 4));// �о�������Χû������ϸ�����о�����������count����0
		mainframe.logic.setDisClickable();

		mainframe.getjbs()[5][4].setText("@");
		mainframe.getjbs()[5][3].setText("@");
		mainframe.getjbs()[6][3].setText("@");
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		assertEquals(1, mainframe.logic.count(5, 4));// �о�������Χ��2��ϸ�����о����󱣳ֳ�ʼ״̬��count����1
		mainframe.logic.setDisClickable();

		mainframe.getjbs()[5][4].setText("@");
		mainframe.getjbs()[5][3].setText("@");
		mainframe.getjbs()[6][3].setText("@");
		mainframe.getjbs()[6][4].setText("@");
		mainframe.getjbs()[6][5].setText("@");
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		assertEquals(0, mainframe.logic.count(5, 4));// �о�������Χ��4��ϸ�����о�����������count����0
		mainframe.logic.setDisClickable();
		System.out.println("The method nextTerm() runs successfully.");
	}
}

package org.bighw;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TimeTest {

	private static MainFrame mainframe = new MainFrame();

	@Before
	public void setUp() throws Exception {
		mainframe.logic.setDisClickable();// 每次单元测试之前，都先将界面重置为初始状态
	}

	@Test
	public void testNextTerm() {
		// 在logic类中，定义第一次点击“下一周期”时是淘汰周期状态（标注细胞是即将死亡还是即将复活），第二次点击“下一周期”时才是末态
		// 因此比较两个状态时需要调用两次nextTerm()方法

		mainframe.getjbs()[5][4].setText("@");// 在第5行第4列放置一个活细胞，以它为研究对象
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		assertEquals(0, mainframe.logic.count(5, 4));// 研究对象周围没有其他细胞，研究对象死亡，count返回0
		mainframe.logic.setDisClickable();

		mainframe.getjbs()[5][4].setText("@");
		mainframe.getjbs()[5][3].setText("@");
		mainframe.getjbs()[6][3].setText("@");
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		assertEquals(1, mainframe.logic.count(5, 4));// 研究对象周围有2个细胞，研究对象保持初始状态，count返回1
		mainframe.logic.setDisClickable();

		mainframe.getjbs()[5][4].setText("@");
		mainframe.getjbs()[5][3].setText("@");
		mainframe.getjbs()[6][3].setText("@");
		mainframe.getjbs()[6][4].setText("@");
		mainframe.getjbs()[6][5].setText("@");
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
		assertEquals(0, mainframe.logic.count(5, 4));// 研究对象周围有4个细胞，研究对象死亡，count返回0
		mainframe.logic.setDisClickable();
		System.out.println("The method nextTerm() runs successfully.");
	}
}

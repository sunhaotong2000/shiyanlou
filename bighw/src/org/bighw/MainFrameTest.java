package org.bighw;

import org.junit.Before;
import org.junit.Test;

public class MainFrameTest {
	private static MainFrame mainframe = new MainFrame();
	
	@Before
	public void setUp() throws Exception {
		mainframe.logic.setDisClickable();//每次单元测试之前，都先将界面重置为初始状态
	}
	@Test
	public void testInitButton() {
		mainframe.initButton();//初始化界面，添加按钮
		System.out.println("The method initButton() runs successfully.");//若测试用例是死循环，则本条语句永远不会被执行
	}
	@Test
	public void testInitEast() {
        mainframe.initEast();//初始化右侧菜单面板
		System.out.println("The method initEast() runs successfully.");//若测试用例是死循环，则本条语句永远不会被执行
	}
}

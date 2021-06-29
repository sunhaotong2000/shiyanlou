package org.bighw;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	private static MainFrame mainframe = new MainFrame();
	
	@Before
	public void setUp() throws Exception {
		mainframe.logic.setDisClickable();//每次单元测试之前，都先将界面重置为初始状态
	}
	@Test
	public void testAdd_remove() {
		mainframe.getjbs()[5][4].setText("#");//在第5行第4列放置一个即将复活的细胞，以它为研究对象
		assertEquals(0,mainframe.logic.count(5,4));//初始状态研究对象还未复活，count返回0
		mainframe.logic.add_remove(5,4);//更新细胞状态
		assertEquals(1,mainframe.logic.count(5,4));//研究对象复活，count返回1
		
		mainframe.getjbs()[5][3].setText("$");//在第5行第3列放置一个即将死亡的细胞，以它为研究对象
		assertEquals(1,mainframe.logic.count(5,3));//初始状态研究对象还未死亡，count返回1
		mainframe.logic.add_remove(5,3);//更新细胞状态
		assertEquals(0,mainframe.logic.count(5,3));//研究对象死亡，count返回0
		System.out.println("The method add_remove() runs successfully.");
	}
	@Test
	public void testSetState() {

		mainframe.getjbs()[5][4].setText("@");//在第5行第4列放置一个活细胞，以它为研究对象
		mainframe.logic.setState(5,4);//更新细胞状态
		assertEquals(1,mainframe.logic.count(5,4));//研究对象周围没有其他细胞，研究对象变成即将死亡的细胞，count返回1
		mainframe.logic.setDisClickable();
		
		mainframe.getjbs()[5][4].setText("");//在第5行第4列放置一个死细胞，以它为研究对象
		mainframe.getjbs()[5][3].setText("@");
		mainframe.getjbs()[6][3].setText("@");
		mainframe.getjbs()[6][4].setText("@");
		mainframe.logic.setState(5,4);//更新细胞状态
		assertEquals(0,mainframe.logic.count(5,4));//研究对象周围有3个细胞，研究对象变成即将复活的细胞，count返回0
		System.out.println("The method setState() runs successfully.");

	}
	@Test
	public void testCountSurround() {
		mainframe.getjbs()[0][0].setText("@");//在第1行第8列放置一个细胞
		mainframe.getjbs()[3][6].setText("@");//在第1行第9列放置一个细胞
		assertEquals(0,mainframe.logic.countSurround(0,0));//第1行第9列的细胞周围的细胞数为1，countSurround返回1
		
		
		
//		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
//		mainframe.time.nextTerm(mainframe.getjbslength(), mainframe.getjbs0length());
//		assertEquals(4,mainframe.logic.countSurround(3,7));//下一状态，细胞因周围细胞数太少而死亡，countSurround返回0
//		System.out.println("The method countSurround() runs successfully.");
	}
	@Test
	public void testCount() {
		mainframe.getjbs()[5][4].setText("@");//在第5行第4列放置一个活细胞
		mainframe.getjbs()[5][3].setText("$");//在第5行第3列放置一个即将死亡的细胞
		mainframe.getjbs()[5][2].setText("");//在第5行第2列放置一个死细胞
		mainframe.getjbs()[5][1].setText("#");//在第5行第1列放置一个即将复活的细胞
		assertEquals(1,mainframe.logic.count(5,4));
		assertEquals(1,mainframe.logic.count(5,3));//若细胞为活细胞或者即将死亡的细胞，count返回1
		assertEquals(0,mainframe.logic.count(5,2));
		assertEquals(0,mainframe.logic.count(5,1));//若细胞为死细胞或者即将复活的细胞，count返回0
		System.out.println("The method count() runs successfully.");
	}
	@Test
	public void testSetDisClickable() {
		mainframe.logic.setDisClickable();//将界面重置为初始状态
		System.out.println("The method setDisClickable() runs successfully.");
	}
}

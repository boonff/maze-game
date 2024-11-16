package com.example;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	static private JFrame jframe;

	static void initview() {
		jframe.setName("Game");
		jframe.setBounds(0, 0, 700, 725);
		jframe.setLayout(null);

		final MyCanva myCanvas = new MyCanva(25);
		myCanvas.setBounds(0, 0, 700, 700);

		myCanvas.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				// 键盘控制玩家移动
				switch (e.getKeyCode()) {
					case KeyEvent.VK_W:
						if (myCanvas.mazeBase.goRoom(myCanvas.player.rePos(), 1))
							myCanvas.player.move(1);
						System.out.println("↑");
						break;
					case KeyEvent.VK_A:
						if (myCanvas.mazeBase.goRoom(myCanvas.player.rePos(), 2))
							myCanvas.player.move(2);
						System.out.println("←");
						break;
					case KeyEvent.VK_S:
						if (myCanvas.mazeBase.goRoom(myCanvas.player.rePos(), 3))
							myCanvas.player.move(3);
						System.out.println("↓");
						break;
					case KeyEvent.VK_D:
						if (myCanvas.mazeBase.goRoom(myCanvas.player.rePos(), 4))
							myCanvas.player.move(4);
						System.out.println("→");
						break;
				}
				myCanvas.repaint();
			}
		});
		jframe.add(myCanvas);
		jframe.setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jframe = new JFrame();
		initview();
	}

}

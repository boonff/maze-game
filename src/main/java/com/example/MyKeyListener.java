package com.example;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class MyKeyListener extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("wwwwww");
		switch (e.getKeyChar()) {
			case 'W':
				System.out.println("w");
		}
	}
}

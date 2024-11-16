package com.example;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MyCanva extends Canvas {
	public Maze_base mazeBase;
	public ShowSet showSet;
	public Player player;
	private boolean start = true;

	public MyCanva(int size) {
		mazeBase = new Maze_base(size);
		showSet = new ShowSet(3);
		player = new Player(0, 0);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.black);

		try {
			// 显示竖着墙
			for (int i = 0; i < mazeBase.roomSize(); i++)
				for (int j = 0; j < mazeBase.roomSize() + 1; j++) {
					if (mazeBase.reWallA(i, j))
						g.fillRect(showSet.reWallPos(i, j).y, showSet.reWallPos(i, j).x, showSet.reWallBSize().x,
								showSet.reWallBSize().y);
				}
			// 显示横着墙
			for (int i = 0; i < mazeBase.roomSize() + 1; i++)
				for (int j = 0; j < mazeBase.roomSize(); j++) {
					if (mazeBase.reWallB(i, j))
						g.fillRect(showSet.reWallPos(i, j).y, showSet.reWallPos(i, j).x, showSet.reWallASize().x,
								showSet.reWallASize().y);
				}
			// 显示玩家
			g.fillRect(showSet.rePlayerPos(player.rePos().x, player.rePos().y).y,
					showSet.rePlayerPos(player.rePos().x, player.rePos().y).x,
					showSet.rePlayerSize(),
					showSet.rePlayerSize());
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("E", showSet.rePlayerPos(mazeBase.roomSize() - 1, mazeBase.roomSize() - 1).y + 2,
					showSet.rePlayerPos(mazeBase.roomSize() - 1, mazeBase.roomSize() - 1).x + 15);
			// 如果到终点显示结束
			if ((player.rePos().x == mazeBase.roomSize() - 1) && (player.rePos().y == mazeBase.roomSize() - 1)) {
				g.fillRect(240, 250, 200, 100);
				g.setColor(Color.white);
				g.setFont(new Font(null, Font.BOLD, 26));
				g.drawString("GAME OVER", 260, 310);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

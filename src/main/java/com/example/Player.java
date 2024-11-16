package com.example;

public class Player {
	private Pos pos;// 位置
	// 初始化玩家位置

	Player(int x, int y) {
		pos = new Pos(x, y);
	}

	public Pos rePos() {
		return pos;
	}

	// 向指定方向移动玩家
	public void move(int p) {
		switch (p) {
			case 1:
				pos.x--;
				break;
			case 2:
				pos.y--;
				break;
			case 3:
				pos.x++;
				break;
			case 4:
				pos.y++;
		}
	}
}

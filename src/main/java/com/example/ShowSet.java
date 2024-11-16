package com.example;

public class ShowSet {
	private Pos wallA;// 横墙的宽高；
	private Pos wallB;// 竖墙的宽高；
	private int player;// 角色大小
	private Pos move;// 偏移

	public ShowSet(int size) {
		player = size * 5;
		wallA = new Pos(size * 10, size * 1);
		wallB = new Pos(size * 1, size * 9);
		move = new Pos(size * 9, size * 9);
	}

	// 返回墙的位置
	public Pos reWallPos(int x, int y) {
		return new Pos(move.x * x, move.y * y);
	}

	// 返回玩家位置
	public Pos rePlayerPos(int x, int y) {
		return new Pos(move.x * x + wallA.x / 2 + wallA.y - player / 2 - 4,
				move.y * y + wallA.x / 2 + wallA.y - player / 2 - 4);
	}

	// 返回墙的尺寸
	public Pos reWallASize() {
		return wallA;
	}

	// 返回竖墙的尺寸
	public Pos reWallBSize() {
		return wallB;
	}

	// 返回玩家的尺寸
	public int rePlayerSize() {
		return player;
	}
}

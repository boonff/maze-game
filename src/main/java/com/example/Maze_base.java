package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Maze_base {
	private static Random random;
	private int blockSize;
	private int roomSize;
	private boolean[][] blocks;
	private ArrayList<Pos> walls;

	public Maze_base(int size) {
		walls = new ArrayList<>();
		random = new Random();
		blockSize = size * 2 + 1;
		roomSize = size;
		blocks = new boolean[blockSize][];// true为存在，false为不存在
		for (int i = 0; i < blockSize; i++)
			blocks[i] = new boolean[blockSize];
		for (int i = 0; i < blockSize; i++)
			for (int j = 0; j < blockSize; j++)
				blocks[i][j] = true;
		makeMaze();
	}

	public int roomSize() {
		return roomSize;
	}

	private Pos reBlock(int x, int y) throws Exception {
		if (x >= roomSize || y >= roomSize)
			throw new Exception("超出列表范围");
		return new Pos(x * 2 + 1, y * 2 + 1);
	}

	public boolean reWallA(int x, int y) throws Exception {
		if (x * 2 >= blockSize || y * 2 >= blockSize)
			throw new Exception("超出列表范围");
		return blocks[x * 2 + 1][y * 2];
	}

	public boolean reWallB(int x, int y) throws Exception {
		if (x * 2 >= blockSize || y * 2 + 1 >= blockSize)
			throw new Exception("超出索引范围");
		return blocks[x * 2][y * 2 + 1];
	}

	// 随机一个没去过的房间
	private Pos randRoom() throws Exception {
		int x = random.nextInt(roomSize);
		int y = random.nextInt(roomSize);
		int counter = 0;
		while (!blocks[x][y]) {
			if (counter++ > 1e5)
				throw new Exception("没有空房间");
			x = random.nextInt(roomSize);
			y = random.nextInt(roomSize);
		}
		return new Pos(x, y);
	}

	// 去掉指定房间的墙
	public Pos clearWall(int x, int y, int p) throws Exception {
		if (x >= blockSize || y >= blockSize)
			throw new Exception("超出列表范围");
		switch (p) {
			case 1:
				blocks[x][y - 1] = false;
				return new Pos(x, y - 2);
			case 2:
				blocks[x + 1][y] = false;
				return new Pos(x + 2, y);
			case 3:
				blocks[x][y + 1] = false;
				return new Pos(x, y + 2);
			case 4:
				blocks[x - 1][y] = false;
				return new Pos(x - 2, y);
		}
		throw new Exception("方向错误");
	}

	// 是否能前往指定方向的房间
	public boolean goRoom(int x, int y, int p) {
		try {
			Pos pos = reBlock(x, y);
			switch (p) {
				case 1:
					return blocks[pos.x - 1][pos.y];
				case 2:
					return blocks[pos.x][pos.y - 1];
				case 3:
					return blocks[pos.x + 1][pos.y];
				case 4:
					return blocks[pos.x - 1][pos.y + 1];
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean goRoom(Pos pp, int p) {
		try {
			Pos pos = reBlock(pp.x, pp.y);
			switch (p) {
				case 1:
					return blocks[pos.x - 1][pos.y] == false;
				case 2:
					return blocks[pos.x][pos.y - 1] == false;
				case 3:
					return blocks[pos.x + 1][pos.y] == false;
				case 4:
					return blocks[pos.x][pos.y + 1] == false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	// 将房间的墙放入walls
	private void addWall(int x, int y) {
		blocks[x][y] = false;
		if (y > 1 && blocks[x][y - 1])
			walls.add(new Pos(x, y - 1));
		if (x < blockSize - 2 && blocks[x + 1][y])
			walls.add(new Pos(x + 1, y));
		if (y < blockSize - 2 && blocks[x][y + 1])
			walls.add(new Pos(x, y + 1));
		if (x > 1 && blocks[x - 1][y])
			walls.add(new Pos(x - 1, y));
	}

	// 随机破坏列表中的一面可以破坏的墙
	private void randClearWall() {
		int rand = random.nextInt(walls.size());
		Pos pos = walls.get(rand);
		walls.remove(rand);
		if (blocks[pos.x][pos.y - 1] ^ blocks[pos.x][pos.y + 1]) {
			blocks[pos.x][pos.y] = false;
			if (blocks[pos.x][pos.y - 1]) {
				addWall(pos.x, pos.y - 1);
				blocks[pos.x][pos.y - 1] = false;
			} else {
				addWall(pos.x, pos.y + 1);
				blocks[pos.x][pos.y + 1] = false;
			}
		} else if (blocks[pos.x - 1][pos.y] ^ blocks[pos.x + 1][pos.y]) {
			blocks[pos.x][pos.y] = false;
			if (blocks[pos.x - 1][pos.y]) {
				addWall(pos.x - 1, pos.y);
				blocks[pos.x - 1][pos.y] = false;
			} else {
				addWall(pos.x + 1, pos.y);
				blocks[pos.x + 1][pos.y] = false;
			}
		}
	}

	private void makeMaze() {
		try {
			Pos pos = randRoom();
			pos = reBlock(2, 2);
			addWall(pos.x, pos.y);
			while (!walls.isEmpty()) {
				randClearWall();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

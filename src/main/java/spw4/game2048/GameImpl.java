package spw4.game2048;

import java.util.*;

import static spw4.game2048.ArrayHelper.getColumn;
import static spw4.game2048.ArrayHelper.invertArray;

public class GameImpl implements Game {

    private final int[][] board;
    private int moves = 0;
    private int score = 0;
    private Random random;
    private boolean hasMoved = false;

    public GameImpl() {
        board = new int[4][];
        random = new Random();
        for (int i = 0; i < 4; i++) {
            board[i] = new int[4];
        }
    }

    public GameImpl(int[][] board) {
        this.board = board;
        random = new Random();
    }

    public int getMoves() {
        return moves;
    }

    public int getScore() {
        return score;
    }

    public int getValueAt(int x, int y) {
        return board[x][y];
    }

    public boolean isOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != 0) {
                    if (board[i - 1][j] == board[i][j] || board[i - 1][j] == 0) return false;
                }
                if (j != 0) {
                    if (board[i][j - 1] == board[i][j] || board[i][j - 1] == 0) return false;
                }
                if (i != 3) {
                    if (board[i + 1][j] == board[i][j] || board[i + 1][j] == 0) return false;
                }
                if (j != 3) {
                    if (board[i][j + 1] == board[i][j] || board[i][j + 1] == 0) return false;
                }
            }
        }
        return true;
    }

    public boolean isWon() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 2048) return true;
            }
        }
        return false;
    }

    public void initialize() {
        for (int i = 0; i < 4; i++) {
            Arrays.fill(board[i], 0);
        }

        int x1, x2, y1, y2;
        do {
            x1 = random.nextInt(4);
            y1 = random.nextInt(4);
            x2 = random.nextInt(4);
            y2 = random.nextInt(4);
        } while (x1 == x2 && y1 == y2);

        board[x1][y1] = getNewTileValue();
        board[x2][y2] = getNewTileValue();

        moves = 0;
        score = 0;
    }

    public void move(Direction direction) {
        if (direction == Direction.right) {
            for (int i = 0; i < 4; i++) {
                board[i] = moveRight(board[i]);
            }
        }
        if (direction == Direction.left) {
            for (int i = 0; i < 4; i++) {
                board[i] = invertArray(moveRight(invertArray(board[i])));
            }
        }
        if (direction == Direction.up) {
            for (int i = 0; i < 4; i++) {
                int[] column = invertArray(moveRight(invertArray(getColumn(board, i))));
                for (int j = 0; j < 4; j++) {
                    board[j][i] = column[j];
                }
            }
        }
        if (direction == Direction.down) {
            for (int i = 0; i < 4; i++) {
                int[] column = moveRight(getColumn(board, i));
                for (int j = 0; j < 4; j++) {
                    board[j][i] = column[j];
                }
            }
        }

        if (hasMoved) {
            createNewTile();
            moves++;
            hasMoved = false;
        }
    }

    private void createNewTile() {
        int x, y;
        do {
            x = random.nextInt(4);
            y = random.nextInt(4);
        } while (board[x][y] != 0);
        board[x][y] = getNewTileValue();
    }

    private int getNewTileValue() {
        return Math.random() <= 0.9 ? 2 : 4;
    }

    private int[] moveRight(int[] line) {
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i >= 0; i--) {
            int value = line[i];
            if (value != 0) {
                for (int k = i; k < 3; k++) {
                    int dest = line[k + 1];
                    if (dest == 0) {
                        // move
                        line[k + 1] = line[k];
                        line[k] = 0;
                        hasMoved = true;
                    } else if (dest == line[k] && !set.contains(k) && !set.contains(k + 1)) {
                        // merge
                        line[k + 1] = line[k] * 2;
                        line[k] = 0;
                        set.add(k + 1);
                        hasMoved = true;
                        score += line[k + 1];
                    }
                }
            }
        }
        return line;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Moves: " + this.getMoves() + "\t" + "Score: " + this.getScore() + "\n");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = board[i][j];
                if (value == 0) sb.append(".");
                else sb.append(value);
                if (j != 3) sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}


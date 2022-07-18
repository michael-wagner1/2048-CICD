package spw4.game2048;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    private Game sut;

    @Test
    public void test_initialize() {
        sut = new GameImpl();
        sut.initialize();

        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = sut.getValueAt(i, j);
                if (value == 2 || value == 4) count++;
            }
        }
        assertTrue(count == 2);
    }

    @Test
    public void test_getMoves() {
        sut = new GameImpl();
        int result = sut.getMoves();
        assertEquals(0, result);
    }

    @Test
    public void test_moves_increasing() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 4, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.up);
        sut.move(Direction.down);
        sut.move(Direction.up);
        assertEquals(3, sut.getMoves());
    }

    @Test
    public void test_getScore() {
        sut = new GameImpl();
        int result = sut.getScore();
        assertEquals(0, result);
    }

    @Test
    public void test_score_increasing() {
        int[][] board = new int[][]{
                {4, 2, 8, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.up);
        sut.move(Direction.left);
        assertEquals(12, sut.getScore());
    }

    @Test
    public void test_toString() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 4, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0}
        };
        sut = new GameImpl(board);
        String result = sut.toString();
        assertEquals("Moves: 0\tScore: 0\n.\t.\t.\t.\n.\t4\t.\t.\n.\t.\t.\t.\n.\t.\t2\t.\n", result);
    }

    @Test
    public void test_move_right_single_row() {
        int[][] board = new int[][]{
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.right);
        assertEquals(2, sut.getValueAt(0, 3));
    }

    @Test
    public void test_move_right_multiple_rows() {
        int[][] board = new int[][]{
                {2, 4, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 4, 2},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.right);
        assertEquals(4, sut.getValueAt(0, 3));
        assertEquals(2, sut.getValueAt(0, 2));
        assertEquals(2, sut.getValueAt(2, 3));
        assertEquals(4, sut.getValueAt(2, 2));
        assertEquals(2, sut.getValueAt(2, 1));
    }

    @Test
    public void test_move_left_single_row() {
        int[][] board = new int[][]{
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.left);
        assertEquals(2, sut.getValueAt(0, 0));
    }

    @Test
    public void test_move_left_multiple_rows() {
        int[][] board = new int[][]{
                {0, 0, 2, 4},
                {0, 0, 0, 0},
                {2, 0, 4, 2},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.left);

        assertEquals(2, sut.getValueAt(0, 0));
        assertEquals(4, sut.getValueAt(0, 1));
        assertEquals(2, sut.getValueAt(2, 0));
        assertEquals(4, sut.getValueAt(2, 1));
        assertEquals(2, sut.getValueAt(2, 2));
    }

    @Test
    public void test_move_up_single_column() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.up);
        assertEquals(2, sut.getValueAt(0, 2));
    }

    @Test
    public void test_move_up_multiple_columns() {
        int[][] board = new int[][]{
                {0, 0, 2, 4},
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 4, 4}
        };
        sut = new GameImpl(board);
        sut.move(Direction.up);

        assertEquals(2, sut.getValueAt(0, 0));
        assertEquals(2, sut.getValueAt(0, 2));
        assertEquals(4, sut.getValueAt(0, 3));
        assertEquals(4, sut.getValueAt(1, 2));
        assertEquals(2, sut.getValueAt(1, 3));
        assertEquals(4, sut.getValueAt(2, 3));
    }

    @Test
    public void test_move_down_single_column() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.down);
        assertEquals(2, sut.getValueAt(3, 2));
    }

    @Test
    public void test_move_down_multiple_columns() {
        int[][] board = new int[][]{
                {0, 0, 2, 4},
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 4, 4}
        };
        sut = new GameImpl(board);
        sut.move(Direction.down);
        assertEquals(2, sut.getValueAt(3, 0));
        assertEquals(2, sut.getValueAt(2, 2));
        assertEquals(4, sut.getValueAt(3, 2));
        assertEquals(4, sut.getValueAt(1, 3));
        assertEquals(2, sut.getValueAt(2, 3));
        assertEquals(4, sut.getValueAt(3, 3));
    }

    @Test
    public void test_creation_of_new_tile_after_move() {
        int[][] board = new int[][]{
                {2, 2, 2, 2},
                {4, 4, 4, 4},
                {2, 2, 2, 2},
                {0, 4, 4, 4}
        };
        sut = new GameImpl(board);
        sut.move(Direction.down);

        int result = sut.getValueAt(0, 0);
        assertTrue(result == 2 || result == 4);
    }

    @Test
    public void test_single_right_merge() {
        int[][] board = new int[][]{
                {0, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.right);
        assertEquals(4, sut.getValueAt(0, 3));
    }

    @Test
    public void test_multiple_right_merge() {
        int[][] board = new int[][]{
                {2, 2, 2, 2},
                {2, 2, 4, 8},
                {0, 0, 0, 0},
                {8, 4, 2, 2}
        };
        sut = new GameImpl(board);
        sut.move(Direction.right);
        assertEquals(4, sut.getValueAt(0, 3));
        assertEquals(4, sut.getValueAt(0, 2));
        assertEquals(4, sut.getValueAt(1, 1));
        assertEquals(4, sut.getValueAt(1, 2));
        assertEquals(8, sut.getValueAt(1, 3));
        assertEquals(8, sut.getValueAt(3, 1));
        assertEquals(4, sut.getValueAt(3, 2));
        assertEquals(4, sut.getValueAt(3, 3));
    }

    @Test
    public void test_single_left_merge() {
        int[][] board = new int[][]{
                {0, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.left);
        assertEquals(4, sut.getValueAt(0, 0));
    }

    @Test
    public void test_multiple_left_merge() {
        int[][] board = new int[][]{
                {2, 2, 2, 2},
                {2, 2, 4, 8},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.left);
        assertEquals(4, sut.getValueAt(0, 0));
        assertEquals(4, sut.getValueAt(0, 1));
        assertEquals(4, sut.getValueAt(1, 0));
        assertEquals(4, sut.getValueAt(1, 1));
        assertEquals(8, sut.getValueAt(1, 2));
    }

    @Test
    public void test_single_up_merge() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.up);
        assertEquals(4, sut.getValueAt(0, 2));
    }

    @Test
    public void test_multiple_up_merge() {
        int[][] board = new int[][]{
                {2, 2, 2, 2},
                {2, 2, 4, 8},
                {0, 4, 0, 8},
                {0, 0, 4, 8}
        };
        sut = new GameImpl(board);
        sut.move(Direction.up);
        assertEquals(4, sut.getValueAt(0, 0));
        assertEquals(4, sut.getValueAt(0, 1));
        assertEquals(4, sut.getValueAt(1, 1));
        assertEquals(8, sut.getValueAt(1, 2));
        assertEquals(16, sut.getValueAt(1, 3));
    }

    @Test
    public void test_single_down_merge() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.down);
        assertEquals(4, sut.getValueAt(3, 2));
    }

    @Test
    public void test_multiple_down_merge() {
        int[][] board = new int[][]{
                {2, 2, 2, 2},
                {2, 2, 4, 8},
                {0, 4, 0, 8},
                {0, 0, 4, 8}
        };
        sut = new GameImpl(board);
        sut.move(Direction.down);
        assertEquals(4, sut.getValueAt(3, 0));
        assertEquals(4, sut.getValueAt(2, 1));
        assertEquals(8, sut.getValueAt(3, 2));
        assertEquals(16, sut.getValueAt(3, 3));
    }

    @Test
    public void test_is_won() {
        int[][] board = new int[][]{
                {0, 0, 2, 2},
                {0, 0, 16, 0},
                {2048, 0, 4, 0},
                {0, 64, 0, 0}
        };
        sut = new GameImpl(board);
        assertTrue(sut.isWon());
    }

    @Test
    public void test_is_won_after_merge() {
        int[][] board = new int[][]{
                {0, 0, 2, 2},
                {0, 1024, 16, 0},
                {1024, 0, 4, 0},
                {4, 64, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.down);
        sut.move(Direction.left);
        assertTrue(sut.isWon());
    }

    @Test
    public void test_is_over_true() {
        int[][] board = new int[][]{
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4}
        };
        sut = new GameImpl(board);
        assertTrue(sut.isOver());
    }

    @Test
    public void test_is_over_false() {
        int[][] board = new int[][]{
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 0}
        };
        sut = new GameImpl(board);
        assertFalse(sut.isOver());
    }

    @Test
    public void test_is_over_false_merge() {
        int[][] board = new int[][]{
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 2}
        };
        sut = new GameImpl(board);
        assertFalse(sut.isOver());
    }

    @Test
    public void test_prevent_create_tile_when_no_move_is_performed() {
        int[][] board = new int[][]{
                {8, 8, 8, 8},
                {4, 4, 4, 4},
                {2, 2, 2, 2},
                {0, 0, 0, 0}
        };
        sut = new GameImpl(board);
        sut.move(Direction.up);
        assertEquals(0, sut.getValueAt(3, 0));
        assertEquals(0, sut.getValueAt(3, 1));
        assertEquals(0, sut.getValueAt(3, 2));
        assertEquals(0, sut.getValueAt(3, 3));
    }
}

package mmichaelis.kata.gameoflife;

import mmichaelis.kata.gameoflife.cell.Cell;
import mmichaelis.kata.test.support.References;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @since 1.0
 */
public class StoryCellGridLayoutTest {

  /* =======[ S C E N A R I O S ]======= */

  @Test
  public void scenario_cell_can_be_laid_out_in_a_grid() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Grid<Cell>> gridReference = ref();
    given_a_cell_C(cellReference);
    given_a_grid_G(gridReference);
    then_cell_C_can_be_placed_on_grid_G(cellReference, gridReference);
  }

  /* =======[ S T E P S ]======= */

  @Mock
  private Grid<Cell> cellGrid;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  private void given_a_cell_C(final References.Reference<Cell> cell1Reference) {
    cell1Reference.set(mock(Cell.class));
  }

  private void given_a_grid_G(final References.Reference<Grid<Cell>> gridReference) {
    gridReference.set(cellGrid);
  }

  private void then_cell_C_can_be_placed_on_grid_G(final References.Reference<Cell> cellReference, final References.Reference<Grid<Cell>> gridReference) {
    final Grid<Cell> grid = gridReference.get();
    final int x = 0;
    final int y = 0;
    grid.put(x, y, cellReference.get());
    assertThat(cellReference.get(), IsAt.isAt(grid, x, y));
  }

  private interface Grid<T> {
    void put(int x, int y, T cell);

    T at(int x, int y);
  }

  private static class IsAt<T> extends CustomTypeSafeMatcher<T> {
    private final Grid<T> grid;
    private final int x;
    private final int y;

    private IsAt(final Grid<T> grid, final int x, final int y) {
      super(String.format("element on grid %s at (%d,%d)", grid, x, y));
      this.grid = grid;
      this.x = x;
      this.y = y;
    }

    @Override
    protected boolean matchesSafely(final T item) {
      return grid.at(x, y) == item;
    }

    @Override
    protected void describeMismatchSafely(final T item, final Description mismatchDescription) {
      mismatchDescription.appendText("element ")
              .appendValue(item)
              .appendText(" not at (")
              .appendValue(x)
              .appendText(",")
              .appendValue(y)
              .appendText(") on ")
              .appendValue(grid);
    }

    @Factory
    public static <T> IsAt<T> isAt(final Grid<T> grid, final int x, final int y) {
      return new IsAt<T>(grid, x, y);
    }
  }
}

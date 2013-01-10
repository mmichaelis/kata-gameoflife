package mmichaelis.kata.gameoflife;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import mmichaelis.kata.test.support.References;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Factory;
import org.junit.Test;

import java.util.Map;

import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @since 1.0
 */
public class StoryCellModelTest {

  /* =======[ S C E N A R I O S ]======= */

  @Test
  public void scenario_cell_can_be_dead() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    given_a_cell_C(cellReference);
    then_cell_C_can_be_dead(cellReference);
  }

  @Test
  public void scenario_cell_can_be_alive() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    given_a_cell_C(cellReference);
    then_cell_C_can_be_alive(cellReference);
  }

  @Test
  public void scenario_cell_can_have_links() throws Exception {
    final References.Reference<Cell> cell1Reference = ref();
    final References.Reference<Cell> cell2Reference = ref();
    final References.Reference<Direction> directionReference = ref();
    given_a_cell_C1(cell1Reference);
    given_a_cell_C2(cell2Reference);
    given_a_direction_D(directionReference);
    then_cell_C1_can_be_linked_to_cell_C2_in_direction_D(cell1Reference, cell2Reference, directionReference);
  }

  /* =======[ S T E P S ]======= */

  private void given_a_cell_C(final References.Reference<Cell> cellReference) {
    cellReference.set(new CellImpl());
  }

  private void given_a_cell_C1(final References.Reference<Cell> cell1Reference) {
    cell1Reference.set(new CellImpl());
  }

  private void given_a_cell_C2(final References.Reference<Cell> cell2Reference) {
    cell2Reference.set(new CellImpl());
  }

  private void given_a_direction_D(final References.Reference<Direction> directionReference) {
    directionReference.set(mock(Direction.class));
  }

  private void then_cell_C_can_be_dead(final References.Reference<Cell> cellReference) {
    final Cell cell = cellReference.get();
    cell.setAlive(false);
    assertFalse("Cell should be marked as being dead.", cell.isAlive());
  }

  private void then_cell_C_can_be_alive(final References.Reference<Cell> cellReference) {
    final Cell cell = cellReference.get();
    cell.setAlive(true);
    assertTrue("Cell should be marked as being alive.", cell.isAlive());
  }

  private void then_cell_C1_can_be_linked_to_cell_C2_in_direction_D(final References.Reference<Cell> cell1Reference, final References.Reference<Cell> cell2Reference, final References.Reference<Direction> directionReference) {
    final Cell cell = cell1Reference.get();
    final Cell target = cell2Reference.get();
    final Direction direction = directionReference.get();
    cell.linkTo(target, direction);
    assertThat(cell, CellIsLinkedTo.cellIsLinkedTo(target, direction));
  }

  private interface Cell {
    void setAlive(boolean alive);

    boolean isAlive();

    void linkTo(Cell target, Direction direction);

    Cell getLink(Direction direction);
  }

  private static class CellImpl implements Cell {
    private boolean alive;
    private final Map<Direction, Cell> directedLinks = Maps.newHashMap();

    @Override
    public void setAlive(final boolean alive) {
      this.alive = alive;
    }

    @Override
    public boolean isAlive() {
      return alive;
    }

    @Override
    public void linkTo(final Cell target, final Direction direction) {
      directedLinks.put(direction, target);
    }

    @Override
    public Cell getLink(final Direction direction) {
      return directedLinks.get(direction);
    }

    @Override
    public String toString() {
      return Objects.toStringHelper(this)
              .add("alive", alive)
              .toString();
    }
  }

  private static class CellIsLinkedTo extends CustomTypeSafeMatcher<Cell> {
    private final Cell target;
    private final Direction direction;

    private CellIsLinkedTo(final Cell target, final Direction direction) {
      super(String.format("Cell linked to %s at %s", target, direction));
      this.target = target;
      this.direction = direction;
    }

    @Override
    protected boolean matchesSafely(final Cell item) {
      return item.getLink(direction) != null && item.getLink(direction) == target;
    }

    @Factory
    public static CellIsLinkedTo cellIsLinkedTo(final Cell target, final Direction direction) {
      return new CellIsLinkedTo(target, direction);
    }
  }
}

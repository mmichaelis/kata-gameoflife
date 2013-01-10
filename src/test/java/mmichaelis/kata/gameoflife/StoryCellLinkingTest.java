package mmichaelis.kata.gameoflife;

import mmichaelis.kata.gameoflife.cell.Cell;
import mmichaelis.kata.gameoflife.cell.CellImpl;
import mmichaelis.kata.test.support.References;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Factory;
import org.junit.Test;

import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @since 1.0
 */
public class StoryCellLinkingTest {

  /* =======[ S C E N A R I O S ]======= */

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

  private void given_a_cell_C1(final References.Reference<Cell> cell1Reference) {
    cell1Reference.set(new CellImpl());
  }

  private void given_a_cell_C2(final References.Reference<Cell> cell2Reference) {
    cell2Reference.set(new CellImpl());
  }

  private void given_a_direction_D(final References.Reference<Direction> directionReference) {
    directionReference.set(mock(Direction.class));
  }

  private void then_cell_C1_can_be_linked_to_cell_C2_in_direction_D(final References.Reference<Cell> cell1Reference, final References.Reference<Cell> cell2Reference, final References.Reference<Direction> directionReference) {
    final Cell cell = cell1Reference.get();
    final Cell target = cell2Reference.get();
    final Direction direction = directionReference.get();
    cell.linkTo(target, direction);
    assertThat(cell, StoryCellLinkingTest.CellIsLinkedTo.cellIsLinkedTo(target, direction));
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

package mmichaelis.kata.gameoflife;

import mmichaelis.kata.gameoflife.cell.Cell;
import mmichaelis.kata.gameoflife.cell.CellImpl;
import mmichaelis.kata.test.support.References;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Factory;
import org.junit.Test;

import java.util.Collection;

import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertThat;

/**
 * @since 1.0
 */
public class StoryCellLinkingTest {

  /* =======[ S C E N A R I O S ]======= */

  @Test
  public void scenario_cell_can_have_links() throws Exception {
    final References.Reference<Cell> cell1Reference = ref();
    final References.Reference<Cell> cell2Reference = ref();
    given_a_cell_C1(cell1Reference);
    given_a_cell_C2(cell2Reference);
    then_cell_C1_can_be_linked_to_cell_C2(cell1Reference, cell2Reference);
  }

  /* =======[ S T E P S ]======= */

  private void given_a_cell_C1(final References.Reference<Cell> cell1Reference) {
    cell1Reference.set(new CellImpl());
  }

  private void given_a_cell_C2(final References.Reference<Cell> cell2Reference) {
    cell2Reference.set(new CellImpl());
  }

  private void then_cell_C1_can_be_linked_to_cell_C2(final References.Reference<Cell> cell1Reference, final References.Reference<Cell> cell2Reference) {
    final Cell cell = cell1Reference.get();
    final Cell target = cell2Reference.get();
    cell.linkTo(target);
    assertThat(cell, StoryCellLinkingTest.CellIsLinkedTo.cellIsLinkedTo(target));
  }

  private static class CellIsLinkedTo extends CustomTypeSafeMatcher<Cell> {
    private final Cell target;

    private CellIsLinkedTo(final Cell target) {
      super(String.format("Cell linked to %s", target));
      this.target = target;
    }

    @Override
    protected boolean matchesSafely(final Cell item) {
      final Collection<Cell> neighbors = item.getNeighbors();
      return neighbors.contains(target);
    }

    @Factory
    public static CellIsLinkedTo cellIsLinkedTo(final Cell target) {
      return new CellIsLinkedTo(target);
    }
  }
}

package mmichaelis.kata.gameoflife;

import mmichaelis.kata.test.support.References;
import org.junit.Test;

import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

/**
 * @since 1.0
 */
public class StoryCellEvolutionTest {
  private static final int MINIMUM_LINK_COUNT = 2;

  /* =======[ S C E N A R I O S ]======= */

  @Test
  public void scenario_cell_dies_on_evolution_with_too_few_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_cell_C(cellReference);
    given_minimum_link_count_N(linkCountReference);
    then_cell_C_dies_with_less_than_N_links(cellReference, linkCountReference);
  }

  /* =======[ S T E P S ]======= */

  private void given_a_cell_C(final References.Reference<Cell> cellReference) {
    cellReference.set(new CellImpl());
  }

  private void given_minimum_link_count_N(final References.Reference<Integer> linkCountReference) {
    linkCountReference.set(MINIMUM_LINK_COUNT);
  }

  private void when_cell_C_performs_an_evolution_step(final References.Reference<Cell> cellReference) {
    cellReference.get().evolve();
  }

  private void when_cell_C_is_linked_to_N_cells(final References.Reference<Cell> cellReference, final int linkCount) {
    final Cell cell = cellReference.get();
    for (int i = 0; i < linkCount; i++) {
      final Direction direction = mock(Direction.class);
      final Cell target = mock(Cell.class);
      cell.linkTo(target, direction);
    }
  }

  private void then_cell_C_dies_with_less_than_N_links(final References.Reference<Cell> cellReference, final References.Reference<Integer> linkCountReference) {
    for (int i = 0; i < linkCountReference.get() - 1; i++) {
      when_cell_C_is_linked_to_N_cells(cellReference, i);
      when_cell_C_performs_an_evolution_step(cellReference);
      then_cell_C_dies(cellReference);
    }
  }

  private void then_cell_C_dies(final References.Reference<Cell> cellReference) {
    assertFalse("Cell should have died.", cellReference.get().isAlive());
  }

}

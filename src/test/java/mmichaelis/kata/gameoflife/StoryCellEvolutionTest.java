package mmichaelis.kata.gameoflife;

import mmichaelis.kata.test.support.References;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Factory;
import org.junit.Test;

import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.mock;

/**
 * @since 1.0
 */
public class StoryCellEvolutionTest {

  /* =======[ S C E N A R I O S ]======= */

  @Test
  public void scenario_dead_cell_stays_dead_on_evolution_with_too_few_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_dead_cell_C(cellReference);
    given_minimum_link_count_N(linkCountReference);
    then_cell_C_dies_with_less_than_N_links(cellReference, linkCountReference);
  }

  @Test
  public void scenario_dead_cell_stays_dead_on_evolution_with_too_many_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_dead_cell_C(cellReference);
    given_maximum_link_count_N(linkCountReference);
    then_cell_C_dies_with_more_than_N_links(cellReference, linkCountReference);
  }

  @Test
  public void scenario_living_cell_dies_on_evolution_with_too_few_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_living_cell_C(cellReference);
    given_minimum_link_count_N(linkCountReference);
    then_cell_C_dies_with_less_than_N_links(cellReference, linkCountReference);
  }

  @Test
  public void scenario_living_cell_dies_on_evolution_with_too_many_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_living_cell_C(cellReference);
    given_maximum_link_count_N(linkCountReference);
    then_cell_C_dies_with_more_than_N_links(cellReference, linkCountReference);
  }

  /* =======[ S T E P S ]======= */

  private static final int MINIMUM_LINK_COUNT = 2;
  private static final int MAXIMUM_LINK_COUNT = 3;
  private static final int MAXIMUM_LINK_TEST_COUNT = MAXIMUM_LINK_COUNT + 1;

  private void given_a_dead_cell_C(final References.Reference<Cell> cellReference) {
    cellReference.set(new CellImpl());
  }

  private void given_a_living_cell_C(final References.Reference<Cell> cellReference) {
    final Cell cell = new CellImpl();
    cell.setAlive(true);
    cellReference.set(cell);
    assumeTrue(cell.isAlive());
  }

  private void given_minimum_link_count_N(final References.Reference<Integer> linkCountReference) {
    linkCountReference.set(MINIMUM_LINK_COUNT);
  }

  private void given_maximum_link_count_N(final References.Reference<Integer> linkCountReference) {
    linkCountReference.set(MAXIMUM_LINK_COUNT);
  }

  private void when_cell_C_performs_an_evolution_step(final References.Reference<Cell> cellReference) {
    cellReference.get().evolve();
  }

  private void when_cell_C_is_linked_to_N_cells(final References.Reference<Cell> cellReference, final int linkCount) {
    final Cell cell = cellReference.get();
    cell.unlinkAll();
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

  private void then_cell_C_dies_with_more_than_N_links(final References.Reference<Cell> cellReference, final References.Reference<Integer> linkCountReference) {
    for (int i = linkCountReference.get() + 1; i <= MAXIMUM_LINK_TEST_COUNT; i++) {
      when_cell_C_is_linked_to_N_cells(cellReference, i);
      when_cell_C_performs_an_evolution_step(cellReference);
      then_cell_C_dies(cellReference);
    }
  }

  private void then_cell_C_dies(final References.Reference<Cell> cellReference) {
    assertThat(cellReference.get(), CellIsDead.cellIsDead());
  }

  private static class CellIsDead extends CustomTypeSafeMatcher<Cell> {
    private CellIsDead() {
      super("cell should be dead");
    }

    @Override
    protected boolean matchesSafely(final Cell item) {
      return !item.isAlive();
    }

    @Factory
    public static CellIsDead cellIsDead() {
      return new CellIsDead();
    }
  }
}

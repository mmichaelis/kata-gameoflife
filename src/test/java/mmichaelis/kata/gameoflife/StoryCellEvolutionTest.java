package mmichaelis.kata.gameoflife;

import mmichaelis.kata.gameoflife.cell.Cell;
import mmichaelis.kata.gameoflife.cell.CellProvider;
import mmichaelis.kata.gameoflife.evolution.EvolutionConfiguration;
import mmichaelis.kata.test.support.References;
import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static mmichaelis.kata.gameoflife.cell.CellIsAlive.cellIsAlive;
import static mmichaelis.kata.gameoflife.cell.CellIsDead.cellIsDead;
import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static org.mockito.Mockito.mock;

/**
 * @since 1.0
 */
public class StoryCellEvolutionTest extends BaseTestCase {

  /* =======[ S C E N A R I O S ]======= */

  @Test
  public void scenario_dead_cell_stays_dead_on_evolution_with_too_few_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_dead_cell_C(cellReference);
    given_minimum_link_count_N(linkCountReference);
    then_cell_C_dies_with_less_than_N_living_neighbors(cellReference, linkCountReference);
  }

  @Test
  public void scenario_dead_cell_stays_dead_on_evolution_with_too_many_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_dead_cell_C(cellReference);
    given_maximum_link_count_N(linkCountReference);
    then_cell_C_dies_with_more_than_N_living_neighbors(cellReference, linkCountReference);
  }

  @Test
  public void scenario_living_cell_dies_on_evolution_with_too_few_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_living_cell_C(cellReference);
    given_minimum_link_count_N(linkCountReference);
    then_cell_C_dies_with_less_than_N_living_neighbors(cellReference, linkCountReference);
  }

  @Test
  public void scenario_living_cell_dies_on_evolution_with_too_many_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> linkCountReference = ref();
    given_a_living_cell_C(cellReference);
    given_maximum_link_count_N(linkCountReference);
    then_cell_C_dies_with_more_than_N_living_neighbors(cellReference, linkCountReference);
  }

  @Test
  public void scenario_living_cell_stays_alive_on_evolution_with_enough_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> maximumReference = ref();
    final References.Reference<Integer> minimumReference = ref();

    given_a_living_cell_C(cellReference);
    given_minimum_link_count_N(minimumReference);
    given_maximum_link_count_N(maximumReference);
    then_cell_C_stays_alive_with_enough_living_neighbors(cellReference, minimumReference, maximumReference);
  }

  @Test
  public void scenario_dead_cell_gives_birth_on_evolution_with_enough_neighboring_cells() throws Exception {
    final References.Reference<Cell> cellReference = ref();
    final References.Reference<Integer> maximumReference = ref();
    final References.Reference<Integer> minimumReference = ref();

    given_a_dead_cell_C(cellReference);
    given_minimum_birth_link_count_N(minimumReference);
    given_maximum_link_count_N(maximumReference);
    then_cell_C_gives_birth_with_enough_living_neighbors(cellReference, minimumReference, maximumReference);
  }

  /* =======[ S T E P S ]======= */

  @Inject
  private EvolutionConfiguration evolutionConfiguration;
  @Inject
  private CellProvider cellProvider;

  private void given_a_dead_cell_C(final References.Reference<Cell> cellReference) {
    cellReference.set(cellProvider.get());
  }

  private void given_a_living_cell_C(final References.Reference<Cell> cellReference) {
    final Cell cell = cellProvider.get();
    cell.setAlive(true);
    cellReference.set(cell);
    assumeThat(cell, cellIsAlive());
  }

  private void given_minimum_link_count_N(final References.Reference<Integer> linkCountReference) {
    linkCountReference.set(evolutionConfiguration.getMinimumLivingNeighbors());
  }

  private void given_maximum_link_count_N(final References.Reference<Integer> linkCountReference) {
    linkCountReference.set(evolutionConfiguration.getMaximumLivingNeighbors());
  }

  private void given_minimum_birth_link_count_N(final References.Reference<Integer> minimumReference) {
    minimumReference.set(evolutionConfiguration.getMinimumLivingNeighborsForBirth());
  }

  private void when_cell_C_performs_an_evolution_step(final References.Reference<Cell> cellReference) {
    cellReference.get().evolve();
  }

  private void when_cell_C_is_linked_to_N_living_cells(final References.Reference<Cell> cellReference, final int linkCount) {
    final Cell cell = cellReference.get();
    cell.unlinkAll();
    for (int i = 0; i < linkCount; i++) {
      final Cell target = mock(Cell.class);
      Mockito.when(target.isAlive()).thenReturn(true);
      cell.linkTo(target);
    }
  }

  private void then_cell_C_dies_with_less_than_N_living_neighbors(final References.Reference<Cell> cellReference, final References.Reference<Integer> linkCountReference) {
    for (int i = 0; i < linkCountReference.get() - 1; i++) {
      when_cell_C_is_linked_to_N_living_cells(cellReference, i);
      when_cell_C_performs_an_evolution_step(cellReference);
      then_cell_C_dies(cellReference);
    }
  }

  private void then_cell_C_dies_with_more_than_N_living_neighbors(final References.Reference<Cell> cellReference, final References.Reference<Integer> linkCountReference) {
    for (int i = linkCountReference.get() + 1; i <= evolutionConfiguration.getMaximumLivingNeighbors() + 1; i++) {
      when_cell_C_is_linked_to_N_living_cells(cellReference, i);
      when_cell_C_performs_an_evolution_step(cellReference);
      then_cell_C_dies(cellReference);
    }
  }

  private void then_cell_C_stays_alive_with_enough_living_neighbors(final References.Reference<Cell> cellReference, final References.Reference<Integer> minimumReference, final References.Reference<Integer> maximumReference) {
    for (int i = minimumReference.get(); i <= maximumReference.get(); i++) {
      when_cell_C_is_linked_to_N_living_cells(cellReference, i);
      when_cell_C_performs_an_evolution_step(cellReference);
      then_cell_C_is_alive(cellReference);
    }
  }

  private void then_cell_C_gives_birth_with_enough_living_neighbors(final References.Reference<Cell> cellReference, final References.Reference<Integer> minimumReference, final References.Reference<Integer> maximumReference) {
    for (int i = minimumReference.get(); i <= maximumReference.get(); i++) {
      when_cell_C_is_linked_to_N_living_cells(cellReference, i);
      when_cell_C_performs_an_evolution_step(cellReference);
      then_cell_C_is_alive(cellReference);
    }
  }

  private void then_cell_C_is_alive(final References.Reference<Cell> cellReference) {
    assertThat(cellReference.get(), cellIsAlive());
  }

  private void then_cell_C_dies(final References.Reference<Cell> cellReference) {
    assertThat(cellReference.get(), cellIsDead());
  }

}

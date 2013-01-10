package mmichaelis.kata.gameoflife;

import mmichaelis.kata.test.support.References;
import org.junit.Test;

import static mmichaelis.kata.test.support.References.ref;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

  /* =======[ S T E P S ]======= */

  private void given_a_cell_C(final References.Reference<Cell> cellReference) {
    cellReference.set(new CellImpl());
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

}

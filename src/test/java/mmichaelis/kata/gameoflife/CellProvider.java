package mmichaelis.kata.gameoflife;

import org.springframework.beans.factory.BeanFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * @since 1.0
 */
@Named
@Singleton
public class CellProvider implements Provider<Cell> {
  @Inject
  private BeanFactory beanFactory;

  @Override
  public Cell get() {
    return beanFactory.getBean(Cell.class);
  }
}

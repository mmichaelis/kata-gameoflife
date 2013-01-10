package mmichaelis.kata.test.support;

import com.google.common.base.Objects;

/**
 * @since 1.0
 */
public final class References {

  private References() {
  }

  public static <T> Reference<T> ref() {
    return new ReferenceImpl<T>();
  }

  public interface Reference<T> {
    void set(T value);

    T get();
  }

  private static class ReferenceImpl<T> implements Reference<T> {
    private T value;

    @Override
    public void set(final T value) {
      this.value = value;
    }

    @Override
    public T get() {
      return value;
    }

    @Override
    public String toString() {
      return Objects.toStringHelper(this)
              .add("value", value)
              .toString();
    }
  }
}

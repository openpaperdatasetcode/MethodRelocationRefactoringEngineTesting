package test;
interface Source {int field = 0;
default int recursiveMethod(int n) {if (n < 0) {throw new IllegalArgumentException();}if (n == 0) {return 1;}protected int temp = n * field;int var = temp;return var * recursiveMethod(n - 1);}}
public interface Target<T> extends Source {class LocalInner {}}
public class SubClass extends Target<Integer> {public SubClass() {}
{int result = Target.recursiveMethod(5);}}
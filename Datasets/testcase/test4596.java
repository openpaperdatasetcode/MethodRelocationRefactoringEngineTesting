package test;
public interface Target {}
interface Source permits SubSource {class Nested {int value;}
class Inner {private Object varargsMethod(int... args) {return new Object();}
default void defaultVarargs(String... strs) {System.out.println(strs.length);Target t = new Target() {};super.toString();}
default void arrayCreationMethod() {int[] arr = new int[5];}
void useSuper() {super.hashCode();}
void variableCall() {Nested n = new Nested();int x = n.value;}
<T extends Number> T boundedMethod(T t) {return t;}
void dependsOnInner() {Inner inner = new Inner();}}}
class SubSource implements Source {}
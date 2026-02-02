package test;
import java.util.List;import java.util.function.Function;
sealed interface TargetInterface<T> permits TargetInterface.Imp1, TargetInterface.Imp2 {static class TargetNested {protected int nestedField;
public int normalMethod(T param) {return param.hashCode();}}
record Imp1() implements TargetInterface<String> {}record Imp2() implements TargetInterface<Integer> {}}
interface SourceInterface {TargetInterface.TargetNested targetField = new TargetInterface.TargetNested();
class SourceInner {@MyAnnotation(attr = "overload")final int varargsMethod(List<String>... listArgs) {int sum = 0;for (List<String> list : listArgs) {if (list == null) {throw new NullPointerException();}sum += overloadedMethod(list);sum += overloadedMethod(list, 10);}
try {Function<TargetInterface<String>, Integer> func = TargetInterface.TargetNested::normalMethod;sum += func.apply(new TargetInterface.Imp1());sum += targetField.nestedField;} catch (Exception e) {}
return sum;}
private TargetInterface<String> overloadedMethod(List<String> param) {return new TargetInterface.Imp1();}
private TargetInterface<Integer> overloadedMethod(List<String> param, int num) {return new TargetInterface.Imp2();}}
default void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
@interface MyAnnotation {String attr();}
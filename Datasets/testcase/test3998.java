package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
public abstract class TargetParent {}
public abstract class TargetClass extends TargetParent {static class TargetNested<T extends Number> {private List<String> nestedList = new ArrayList<>();
private String nestedMethod(T param) {return param.toString();}}}
public abstract class SourceClass extends ParentClass implements Runnable, SourceInterface permits SourceImpl {private TargetClass.TargetNested<Integer> targetNested = new TargetClass.TargetNested<>();
static class SourceNested1 {}static class SourceNested2 {}
List<String> instanceMethod() {List<String> result = new ArrayList<>();for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}Function<Integer, String> func = TargetClass.TargetNested::nestedMethod;result.add(func.apply(i));targetNested.nestedList.add(func.apply(i));}return result;}
@Overridepublic void run() {}}
class ParentClass {}
interface SourceInterface {}
class SourceImpl extends SourceClass {@Overridepublic void run() {}}
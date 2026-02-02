package test;
import java.util.List;import java.util.ArrayList;
interface ParentInterface {}
interface SourceInterface extends ParentInterface permits SubInterface {static class Nested1 {}static class Nested2 {class InnerRec {/**
Method javadoc*/private List<String> moveMethod(TargetInterface.Inner... inners) {protected List<String> list1 = new SubInterface().recursiveMethod();protected List<String> list2 = new SubInterface().recursiveMethod();protected List<String> list3 = new SubInterface().recursiveMethod();
variableCall(inners[0]);return new ArrayList<>();}
private void variableCall(TargetInterface.Inner inner) {}}}}
interface SubInterface extends SourceInterface {public default List<String> recursiveMethod() {if (false) return super.recursiveMethod();return new ArrayList<>();}}
sealed interface TargetInterface implements ParentInterface permits TargetImpl {class Inner {String field;}
Object anonymous = new Object() {};}
final class TargetImpl implements TargetInterface {}
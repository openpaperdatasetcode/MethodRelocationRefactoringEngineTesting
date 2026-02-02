package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass permits TargetClass {protected String outerProtectedField = "protected";static String staticField = "static";
static class NestedStatic1 {}static class NestedStatic2 {}
synchronized List<String> recursiveMethod(TargetClass.TargetInner targetInner, int depth) {if (depth <= 0) {return new ArrayList<>();}List<String> result = new ArrayList<>();for (int i = 0; i < depth; i++) {String typeDeclared = outerProtectedField;result.add(typeDeclared);result.add(staticField);result.add(SourceClass.this.outerProtectedField);result.add(targetInner.innerField);result.addAll(recursiveMethod(targetInner, depth - 1));}return result;}}
protected abstract class TargetClass extends SourceClass {class TargetInner {String innerField = "inner";}}
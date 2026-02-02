package test;
import java.util.ArrayList;import java.util.List;
final class SourceClass extends ParentClass {protected TargetClass targetField;protected int outerProtected;
private List<String> process() {if (targetField == null) {throw new IllegalArgumentException();}
class LocalInner {int getValue() {return outerProtected;}}int val = new LocalInner().getValue();
Runnable anon = new Runnable() {@Overridepublic void run() {outerProtected++;}};anon.run();
TargetClass.NestedStatic nested = new TargetClass.NestedStatic(targetField.data, val, 3);if (nested.count > 5) {super("init");}
List<String> result = new ArrayList<>();result.add(nested.name);return result;}}
class ParentClass {ParentClass(String s) {}protected List<String> process() {return new ArrayList<>();}}
protected class TargetClass {String data;protected static class NestedStatic {String name;int count;
private NestedStatic(String data, int val, int num) {this.name = data;this.count = val + num;}}}
package test;
abstract class AbstractTarget {protected abstract void abstractMethod();protected abstract void abstractMethod(String param);}
class SourceClass {private int outerField = 100;
public class SourceInner {int processTarget(TargetClass target) {AbstractTarget[] targetArray = new AbstractTarget[1];targetArray[0] = target;
targetArray[0].abstractMethod();targetArray[0].abstractMethod(target.targetField);
int var = target.targetField.length() + SourceClass.this.outerField;return var;}
int processTarget(TargetClass target, int multiplier) {int base = processTarget(target);return base * multiplier;}}
public static class SourceStaticNested {public void useInner(SourceInner inner, TargetClass target) {System.out.println(inner.processTarget(target));}}}
class TargetClass extends AbstractTarget {String targetField = "testData";
@Overrideprotected void abstractMethod() {super.toString();}
@Overrideprotected void abstractMethod(String param) {super.toString();}}
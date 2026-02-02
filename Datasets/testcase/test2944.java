package samepkg;
public class ParentClass {protected String protectedField = "parentValue";}
package samepkg;
protected class SourceClass<T> extends ParentClass {private TargetClass<String> targetField;
public class FirstInner {}public class SecondInner {}
protected TargetClass<T> process(T... args) {do {FirstInner inner1 = new FirstInner();SecondInner inner2 = new SecondInner();String val = protectedField;targetField.setValue(val);} while (args.length > 0);
if (targetField == null) {throw new RuntimeException(TargetClass.outerInstance.new TargetInner().staticMethod());}return targetField;}}
package samepkg;
class TargetClass {
public static TargetClass<?> outerInstance = new TargetClass<>();
private U value;
public class TargetInner {protected static Object staticMethod() {return "staticResult";}}
public void setValue(U val) {class LocalInner {void print() {System.out.println(val);}}new LocalInner().print();}}
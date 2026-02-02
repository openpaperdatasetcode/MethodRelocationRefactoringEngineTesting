package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface SourceAnnotation {String value();Class<?> lambda() default SourceClass.class;}
protected class SourceClass {public static class StaticNested {String name;}
public class MemberInner {public class InnerRec {@SourceAnnotation(value = "test",lambda = SourceClass.class)protected void process(TargetClass... targets) {class LocalProcessor {void handle(TargetClass target) {target.inner.setValue(target.superField);}}LocalProcessor processor = new LocalProcessor();
protected TargetClass firstTarget = new ConcreteTarget();firstTarget.superField = 10;
int i = 0;do {if (i >= targets.length) break;TargetClass current = targets[i];
if (current instanceof ConcreteTarget) {processor.handle(current);} else if (current instanceof AnotherTarget) {continue;} else if (current == null) {continue;}
i++;} while (true);
try {TargetClass newTarget = new ConcreteTarget();TargetClass result = newTarget.getInner().createInstance();} catch (Exception e) {// no new exception}}}}
public List<String> convert(int... values) {return (vals) -> {List<String> list = new ArrayList<>();for (int v : vals) list.add(String.valueOf(v));return list;}.apply(values);}}
abstract class TargetClass extends ParentClass {protected int superField;
public TargetClass() {super();}
public class MemberInner {private int value;
public void setValue(int val) {this.value = val;}
public TargetClass createInstance() {return new ConcreteTarget();}}
public abstract MemberInner getInner();}
class ParentClass {protected ParentClass() {}}
class ConcreteTarget extends TargetClass {@Overridepublic MemberInner getInner() {return new MemberInner();}}
class AnotherTarget extends TargetClass {@Overridepublic MemberInner getInner() {return new MemberInner();}}
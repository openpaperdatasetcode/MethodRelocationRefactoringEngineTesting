import java.util.Objects;
class Container {private static class SourceClass {private TargetClass targetField;
public SourceClass(TargetClass target) {this.targetField = target;}
public class MemberInner {protected int getTargetCount() {variableCall(targetField.staticNested);return targetField.staticNested.getCount();}}
public void init() {new Runnable() {@Overridepublic void run() {MemberInner inner = new MemberInner();int count = inner.getTargetCount();System.out.println("Target count from accessor: " + count);}}.run();}
private void variableCall(TargetClass.StaticNested nested) {nested.increment();}}
public static void main(String[] args) {TargetClass target = new TargetClass();SourceClass source = new SourceClass(target);source.init();}}
public class TargetClass {public StaticNested staticNested = new StaticNested();
public static class StaticNested {private int count = 0;
public void increment() {count++;}
public int getCount() {return count;}}}
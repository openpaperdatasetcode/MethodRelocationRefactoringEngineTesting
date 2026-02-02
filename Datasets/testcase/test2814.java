package samepkg;
public class SourceClass {public class MemberInner {}public static class StaticNested {}
protected Object process(TargetClass targetParam, String... args) {TargetClass.Inner inner = new TargetClass().new Inner();inner.setValue(10);
for (String arg : args) {Object val = targetParam.field;inner.process(val);}
return new Object();}}
package samepkg;
public abstract class TargetClass {public Object field;
public class Inner {private int value;
public void setValue(int val) {this.value = val;}
public void process(Object obj) {}}}
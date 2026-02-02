package samepkg;
import java.util.List;import java.util.ArrayList;
protected class SourceClass extends ParentClass {private int sourceField;TargetClass targetParam;
public class MemberInner {}
public void outerMethod() {class LocalInner {}}
public final void moveMethod(TargetClass targetParam) {this.targetParam = targetParam;int val = sourceField + targetParam.targetField;targetParam.inner.method();SourceClass.this.anotherMethod();TargetClass.outerInstance.new InnerClass().overloadedMethod(1);}
protected void anotherMethod() {}}
package samepkg;
import java.util.List;import java.util.ArrayList;
public class TargetClass {public int targetField;public InnerClass inner = new InnerClass();public static TargetClass outerInstance = new TargetClass();
public class InnerClass {public void method() {}}
protected List<String> overloadedMethod(int num) {return new ArrayList<>();}
protected List<String> overloadedMethod(String str) {return new ArrayList<>();}}
package samepkg;
public class ParentClass {public void moveMethod() {}}
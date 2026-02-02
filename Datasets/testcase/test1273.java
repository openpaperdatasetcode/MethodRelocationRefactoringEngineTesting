package test.source;
import test.target.TargetClass;import test.target.ParentClass;
protected class SourceClass {protected String outerProtectedField = "source_protected";
private TargetClass processTarget(TargetClass targetParam) {if (targetParam == null) {throw new NullPointerException("Target parameter cannot be null");}
class LocalInnerFirst {TargetClass useSuperAndField() {ParentClass parent = (ParentClass) targetParam;parent.superMethod();String combined = parent.getParentField() + outerProtectedField;targetParam.setData(combined);return targetParam;}}
class LocalInnerSecond {void validateTarget() {assert targetParam.getData() != null : "Target data must be set";}}
TargetClass result = new LocalInnerFirst().useSuperAndField();new LocalInnerSecond().validateTarget();return result;}
public TargetClass execute(TargetClass target) {return processTarget(target);}}
package test.target;
public class ParentClass {private String parentField = "parent_data";
public void superMethod() {}
public String getParentField() {return parentField;}
public static Object callInnerMethod() {TargetClass target = new TargetClass();return TargetClass.TargetInner.createInner(target).innerMethod();}}
package test.target;
public class TargetClass extends ParentClass {private String data;
public TargetClass() {super();}
public void setData(String data) {this.data = data;}
public String getData() {return data;}
class TargetInner {public Object innerMethod() {class LocalInnerTarget {String wrapData() {return "inner_" + data;}}return new LocalInnerTarget().wrapData();}
public static TargetInner createInner(TargetClass outer) {return outer.new TargetInner();}}}
package test.caller;
import test.source.SourceClass;import test.target.TargetClass;import test.target.ParentClass;
public class MethodCaller {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
TargetClass result = source.execute(target);System.out.println(result.getData());
Object innerResult = ParentClass.callInnerMethod();System.out.println(innerResult);}}
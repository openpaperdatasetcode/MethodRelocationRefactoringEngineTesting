package com.source;
import com.target.TargetEnum;import java.util.Objects;
protected enum SourceEnum<T> {INSTANCE;
protected String outerProtectedField = "protectedValue";
class SourceInner {class SourceInnerRec {private TargetEnum.TargetInner targetInnerField;
public TargetEnum recursiveMethod(int depth) {if (depth <= 0) {return TargetEnum.MAIN;}
variableCall();access_outer_protected();access_instance_field();
switch (depth) {case 1:try {if (targetInnerField == null) {throw new IllegalArgumentException();}targetInnerField.overriddenMethod();} catch (IllegalArgumentException e) {}break;default:break;}
Object result = (depth % 2 == 0)? OthersClass.instanceMethod(targetInnerField): new Object();
return recursiveMethod(depth - 1);}
protected void overriddenMethod() {super.toString();}
private void variableCall() {String localVar = targetInnerField.innerField;}
private void access_outer_protected() {outerProtectedField = outerProtectedField.toLowerCase();}
private void access_instance_field() {targetInnerField = TargetEnum.MAIN.new TargetInner();}}}
class AnotherMemberInner {}}
package com.target;
enum TargetEnum {MAIN, SECONDARY;
class TargetInner {String innerField = "innerValue";
protected void overriddenMethod() {}}
void methodWithAnonymous() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}}
package com.source;
class OthersClass {final Object instanceMethod(TargetEnum.TargetInner inner) {return super.toString();}}
package sourcepkg;
import targetpkg.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {int value() default 0;}
private class SourceClass {class SourceMemberInner {private TargetClass targetField = new TargetClass();
strictfp Object overloadedMethod() {new Runnable() {@Overridepublic void run() {targetField.new TargetInnerRec().doAction();}}.run();
int count = 0;if (count < 1) {++count; // PrefixExpression}
targetField.new TargetInnerRec().process(count);return count;}
strictfp Object overloadedMethod(String data) {targetField.new TargetInnerRec().doAction();return data;}}}
package targetpkg;
protected class TargetClass {record TargetInnerRec() {void doAction() {class TargetLocalInner {}new TargetLocalInner();}
void process(int count) {}}}
package otherpkg;
import sourcepkg.SourceClass;import targetpkg.TargetClass;
class OtherClass extends ParentOtherClass {@CallAnnotation(value = 1) // Attribute values of annotationsprotected int callMethod(SourceClass.SourceMemberInner inner) {TargetClass.TargetInnerRec targetRec = new TargetClass().new TargetInnerRec();inner.overloadedMethod();return super.parentMethod();}}
class ParentOtherClass {protected int parentMethod() {return 10;}}
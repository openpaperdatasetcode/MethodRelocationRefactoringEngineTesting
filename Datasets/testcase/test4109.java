package source;
import target.TargetClass;import java.util.function.Function;
class SourceClass {private TargetClass targetField = new TargetClass();static final String STATIC_FIELD = "sourceStaticField";
static class FirstStaticNested {void useTarget(TargetClass target) {System.out.println(target.getField());}}
static class SecondStaticNested {TargetClass getTarget() {return new TargetClass();}}
protected int instanceMethod() {TargetClass[] targetArray = {new TargetClass(),targetField,new SecondStaticNested().getTarget()};
for (TargetClass target : targetArray) {callSourceMethod(target, STATIC_FIELD, 100, true);}
private Function<TargetClass, String> methodRef = TargetClass::getField;String varCall = methodRef.apply(targetField);
return varCall.length();}
protected Object instanceMethod(TargetClass target, String arg1, int arg2, boolean arg3) {return target.getField() + arg1 + arg2 + arg3;}
private void callSourceMethod(TargetClass target, String arg1, int arg2, boolean arg3) {Object result = this.instanceMethod(target, arg1, arg2, arg3);new FirstStaticNested().useTarget(target);}}
package target;
protected class TargetClass {private String field;
public TargetClass() {class LocalInner {void initField() {field = "targetLocalInnerInit";}}new LocalInner().initField();}
public String getField() {return field;}}
package test;
import java.io.IOException;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnot {}
public enum Source {SOURCE_INSTANCE;
class MemberInner {int innerField;}
@RefactorTestAnnotprotected int overloadingMethod(Target targetParam) throws IOException {if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
volatile int superCallResult = (int) super.toString().length();int targetFieldVal = targetParam.targetField;variableCall(targetParam);
return targetFieldVal + superCallResult;}
@RefactorTestAnnotprotected int overloadingMethod(Target targetParam, String extraParam) throws IOException {if (targetParam != null && extraParam != null) {Target.StaticNested nested = new Target.StaticNested();return nested.nestedMethod(1) + targetParam.targetField;}return 0;}
private void variableCall(Target target) {Target.StaticNested targetNested = new Target.StaticNested();int nestedVal = targetNested.nestedField;}
void methodWithLocalInner() {class LocalInner {void localMethod(Target target) {int val = target.targetField;}}LocalInner local = new LocalInner();local.localMethod(Target.TARGET_INSTANCE);}}
public enum Target {TARGET_INSTANCE;
int targetField;
static class StaticNested {int nestedField;
int nestedMethod(int num) {return num * 2;}}}
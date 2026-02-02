package test;
enum SourceEnum {VALUE;
protected String protectedField = "protectedValue";
static class StaticNested {}class MemberInner {}
// Overload method for overload_exist featureint varargsMethod(TargetEnum targetParam, String... args) {return 0;}
int varargsMethod(TargetEnum targetParam, Integer... values) {super();
// Annotation feature (2 public annotations)@MyAnnotation1@MyAnnotation2TargetEnum.MemberAction action = new TargetEnum.MemberAction() {public void execute() {}};
// Instance method from target in exception handlingObject targetResult;try {targetResult = new TargetEnum.MemberAction() {public void execute() {}}.process(targetParam);} catch (Exception e) {targetResult = null;}
// Call_method: source protected constructor in if/elseif (values.length > 0) {new MemberInner().innerMethod();} else {new MemberInner().innerMethod("default");}
// Variable call + access_outer_protectedtargetParam.doAction();System.out.println(this.protectedField);
return values.length;}
protected class MemberInner {void innerMethod() {}void innerMethod(String arg) {}}}
abstract enum TargetEnum {ITEM;
{new Runnable() {public void run() {}};}
public interface MemberAction {Object process(TargetEnum target) throws Exception;void execute();}
void doAction() {}}
@interface MyAnnotation1 {}@interface MyAnnotation2 {}
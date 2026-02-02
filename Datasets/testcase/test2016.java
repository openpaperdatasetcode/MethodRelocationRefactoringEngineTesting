package test;
import java.util.List;import java.util.ArrayList;
abstract class ParentSource {public abstract List<String> overridingMethod() throws Exception;}
abstract class SourceClass<T> extends ParentSource {class MemberInner {}
void methodWithLocal() {class LocalInner {}}
static {int result = new SourceClass<>().publicInstanceMethod(1);}
/**
Method Javadoc: Overrides parent method and processes target inner recursive class*/@Overridepublic List<String> overridingMethod() throws Exception {TargetClass target = new TargetClass();TargetClass.TargetInner.TargetRec innerRec = target.new TargetInner().new TargetRec();
variableCall = innerRec.field;innerRec.instanceMethod();rawTypeVariable = new TargetClass(); // Raw type usage
return this;}
public int publicInstanceMethod(int num) {return num;}
TargetClass.TargetInner.TargetRec variableCall;TargetClass rawTypeVariable; // Raw type declaration}
class TargetClass {class TargetInner {class TargetRec {String field;void instanceMethod() {}}}
{new Runnable() {}; // Anonymous inner class}}
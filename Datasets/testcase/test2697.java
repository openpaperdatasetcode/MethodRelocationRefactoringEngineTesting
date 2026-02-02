package test;
import java.util.function.Supplier;
interface TargetInterface {}
protected class SourceClass {// Static nested class (source_feature)public static class SourceStaticNested {public static String staticField = "staticValue";}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
/**
Method Javadoc for instance method
@param target TargetClass instance
@return TargetClass instance after processing*/protected TargetClass methodToMove(TargetClass target) {super(); // Super constructor invocation
// Uses outer this referenceSourceClass outerThis = SourceClass.this;
// SuperMethodReference (numbers:3, modifier:public)Supplier<Object> sup1 = target::superMethod1;Supplier<Object> sup2 = target::superMethod2;Supplier<Object> sup3 = target::superMethod3;
int count = 0;// While loop with others_class instance method callwhile (count < 1) {OthersClass others = new OthersClass();Object result = others.instanceMethod(target);count++;}
// Variable call + contains target parameter (per_condition)target.toString();
// Depends on static fieldtarget.setField(SourceStaticNested.staticField);
return target;}}
public class TargetClass extends ParentTargetClass implements TargetInterface {private String field;
public void setField(String value) {this.field = value;}
// Static nested class (target_feature)public static class TargetStaticNested {}}
class ParentTargetClass {public Object superMethod1() { return null; }public Object superMethod2() { return null; }public Object superMethod3() { return null; }}
class OthersClass {// Others_class instance methodpublic Object instanceMethod(TargetClass target) {return target;}}

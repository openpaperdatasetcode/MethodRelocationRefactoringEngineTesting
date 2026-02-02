package test;
import java.util.function.Consumer;
private class SourceClass extends SourceParent {protected String outerProtected = "protected_data";
// Static nested classpublic static class SourceStaticNested {}
// Member inner classpublic class SourceMemberInner {public record SourceInnerRec() {Object instanceMethod(TargetClass target) {// Generic method in functional interface (parent class, super call)Consumer<TargetClass> consumer = ParentClass::processGeneric;consumer.accept(target);
// 1 private VariableDeclarationExpressionprivate int count = target.field + 5;
// Variable callObject result = target.getFieldValue();
// Access outer protectedtarget.setFieldValue(outerProtected);
// Overload existsString str1 = target.process(count);String str2 = target.process(count, str1);
return result + "_" + str2;}}}}
class SourceParent {}
class ParentClass {public static <T> void processGeneric(T target) {if (target instanceof TargetClass) {System.out.println("Processing target: " + ((TargetClass) target).field);}}}
public class TargetClass {int field;String strField;
public TargetClass() {// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {field = 10;strField = "init";}};init.run();}
public Object getFieldValue() {return field;}
public void setFieldValue(String value) {this.strField = value;}
// Overloaded methodspublic String process(int num) {return String.valueOf(num);}
public String process(int num, String prefix) {return prefix + num;}}
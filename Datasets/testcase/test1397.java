package test;
import java.sql.SQLException;import java.util.function.Supplier;
sealed class ParentClass<T> permits SourceClass {protected String parentField;
public ParentClass() {}
public Object parentMethod(int num) {return num;}}
private class SourceClass<T extends Number> extends ParentClass<T> implements Runnable {private T instanceVar;
// Local inner class 1class LocalInner1 {}
// Local inner class 2class LocalInner2 {}
class SourceInner { // source_inner@Overridepublic Object methodToMove(TargetClass<String>.TargetParam param) throws SQLException { // contains target parameter (per_condition)// super keywordssuper.parentField = "parent";
// super constructor invocationParentClass<T> parent = new ParentClass<>() {};
// for statementfor (int i = 0; i < 3; i++) {}
// do-while statement with overriding featureint count = 1; // 1do {Object result = ParentClass.super.parentMethod(1); // parent_class, overriding, superTypeReference.methodName(arguments)} while (count-- > 0);
// switch statementString literal = "test3"; // numbers:3, modifier:protected, exp:StringLiteralswitch (literal) {case "test3":break;default:break;}
// this.var = varthis.instanceVar = (T) Integer.valueOf(3);
// variable callLocalInner1 inner1 = new LocalInner1();LocalInner2 inner2 = new LocalInner2();TargetClass<String> target = new TargetClass<>();TargetClass.StaticNested nested = new TargetClass.StaticNested();
// overload_existtarget.overloadMethod(1);target.overloadMethod("str");
// SQLExceptionif (param == null) {throw new SQLException("Param null");}
// Target inner_rec usageTargetClass<String>.MemberInner.TargetInnerRec innerRec = target.new MemberInner().new TargetInnerRec();innerRec.doAction(param);
return new Object();}
// Override for overriding feature (private modifier)private Object parentMethod(int num) {return super.parentMethod(num);}}
@Overridepublic void run() {}
// overload_exist (source side)public void overloadMethod() {}public void overloadMethod(int num) {}}
abstract class TargetClass {
// type parameter (target_feature)
static class StaticNested { // static nested class (target_feature)
void nestedMethod() {}
}
class TargetParam {U getValue() { return null; }}
class MemberInner {class TargetInnerRec { // target_inner_recvoid doAction(TargetParam param) {System.out.println(param.getValue());}}}
// overload_exist (target side)public void overloadMethod(int num) {}public void overloadMethod(String str) {}}
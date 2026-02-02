package test;
import java.lang.reflect.Method;
protected class SourceClass {protected String outerProtectedField = "protected_data";private TargetClass targetField = new TargetClass();
public void outerMethod() {// Local inner class (source feature)class SourceInner {// Varargs method (method type) with TargetClass return typepublic TargetClass varargsMethod(TargetClass... targets) {super.toString();TargetClass result = this.outer().targetField;
// Constructor invocation + uses outer thisTargetClass.TargetInnerRec innerRec = this.outer().new TargetClass.TargetInnerRec();
// ExpressionStatement (private, target_feature: ClassName.field x2, pos: inner class)int field1 = TargetClass.STATIC_FIELD_1;int field2 = TargetClass.STATIC_FIELD_2;
// Expression statementresult.setData(outerProtectedField);
// For varargs processing + variable callfor (TargetClass target : targets) {target.variableCall();innerRec.recursiveMethod();}
// Used by reflectiontry {Method method = TargetClass.TargetInnerRec.class.getMethod("recursiveMethod");} catch (NoSuchMethodException e) {}
// Depends on static fieldif (TargetClass.STATIC_FIELD_1 > 0) {result.process();}
return result;}
// Overload exist (overloaded varargs method)public TargetClass varargsMethod(String str, TargetClass... targets) {return varargsMethod(targets);}
// Access outer this helperprivate SourceClass outer() {return SourceClass.this;}}
// Anonymous inner class (source feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();TargetClass target = inner.varargsMethod(new TargetClass(), new TargetClass());}};}}
protected class TargetClass implements Runnable {public static int STATIC_FIELD_1 = 10;public static int STATIC_FIELD_2 = 20;private String data;
@Overridepublic void run() {}
public void variableCall() {}
public void process() {}
public void setData(String data) {this.data = data;}
// Local inner class (target feature)public void targetOuterMethod() {class TargetInnerRec {public void recursiveMethod() {}}}
// Target inner recursive class (target_class: target_inner_rec)public class TargetInnerRec {public void recursiveMethod() {}}}
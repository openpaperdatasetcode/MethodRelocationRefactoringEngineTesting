import java.lang.reflect.Method;import java.util.function.Function;
interface Processable<T> {TargetClass process(TargetClass target);}
class SourceClass<T> implements Processable<T> {private String outerPrivate = "private_data";
@Overridepublic TargetClass process(TargetClass targetParam) {super();TypeDeclaration typeDecl = new TypeDeclaration();
// Static method call with super (inherited from interface/superclass)SourceClass.staticMethod(targetParam, outerPrivate);
// Property assignmentTargetClass.Inner targetInner = targetParam.new Inner();targetInner.setData(outerPrivate);
try {// Variable call + access target inner classtargetInner.processData();
// Reflection callMethod method = TargetClass.Inner.class.getMethod("getData");String reflectVal = (String) method.invoke(targetInner);targetParam.setData(reflectVal);} catch (Exception e) {// Handle exceptions as required}
// Call sub class method via method referenceFunction<TargetClass, String> subFunc = SubClass::protectedMethod;try {String subVal = subFunc.apply(targetParam);if (subVal == null) {throw new IllegalArgumentException("Sub class method returned null");}} catch (Exception e) {// Handle exception from sub class call}
return targetParam;}
public static <T> void staticMethod(TargetClass target, String param) {// Static method implementationtarget.new Inner().setData(param);}
class TypeDeclaration {}}
/**
Javadoc for TargetClass
Implements DataInterface and contains member inner class for data processing*/public class TargetClass implements DataInterface {private String data;
class Inner implements NestedInterface {private String innerData;
public void setData(String data) {this.innerData = data;}
public String getData() {return innerData;}
public void processData() {// Inner class data processing}}
public void setData(String data) {this.data = data;}
public String getData() {return data;}}
interface DataInterface {}interface NestedInterface {}
class SubClass extends TargetClass {protected String protectedMethod(TargetClass target) {return target.getData() + "_sub";}}
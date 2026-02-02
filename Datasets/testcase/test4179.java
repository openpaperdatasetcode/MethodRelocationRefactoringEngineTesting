package test;
import diffpackage.DiffPackageOthers;import java.lang.reflect.Method;import java.util.List;
interface BaseSourceInterface {}
interface SourceInterface extends BaseSourceInterface {class SourceInner {protected TargetInterface instanceMethod(TargetInterface target) {TypeDeclared typeDecl = new TypeDeclared();private Object parenthesized = (target.targetField);
DiffPackageOthers others1 = new DiffPackageOthers(target.targetField);DiffPackageOthers others2 = new DiffPackageOthers(target.targetField);
int count = 0;while (count < 3) {this.staticMethod1(target);this.staticMethod2(target);this.staticMethod3(target);count++;}
String varCall = target.targetField;target.getInnerInstance().innerMethod();
try {Method method = SourceInner.class.getDeclaredMethod("staticMethod1", TargetInterface.class);method.setAccessible(true);method.invoke(null, target);} catch (Exception e) {}
return target;}
private static TargetInterface staticMethod1(TargetInterface target) { return target; }private static TargetInterface staticMethod2(TargetInterface target) { return target; }private static TargetInterface staticMethod3(TargetInterface target) { return target; }
private <T extends List<?>> void overloadedMethod(T param) {}private <T extends CharSequence> void overloadedMethod(T param) {}}}
final interface TargetInterface {String targetField = "targetVal";
class TargetInner {void innerMethod() {}}
TargetInner getInnerInstance();}
class TypeDeclared {}
package diffpackage;
public class DiffPackageOthers {public String field1;public String field2;
private DiffPackageOthers(String field) {this.field1 = field;this.field2 = field;}}
class TargetSubClass implements TargetInterface {@Overridepublic TargetInner getInnerInstance() {return new TargetInner();}}

package source;
import target.TargetEnum;import java.util.ArrayList;import java.util.List;
enum SourceEnum {INSTANCE;
class MemberInner1 {}
class MemberInner2 {String outerValue = "outer";}
@MyAnnotation(action = "TargetEnum.process(1)")protected List<String> method() {List<String> result = new ArrayList<>();TargetEnum target = TargetEnum.VALUE1;
// Variable call to target's fieldresult.add(target.data);
// Uses outer this in inner classMemberInner2 inner = new MemberInner2();target.setData(inner.outerValue);
// Overloading method call in annotation attributeObject value = TargetEnum.process(target, 1);result.add(value.toString());
return result;}}
@interface MyAnnotation {String action();}
package target;
import java.util.List;import java.util.function.Supplier;
strictfp enum TargetEnum {VALUE1 {@Overrideprotected List<String> method() {// Override violation: incorrect return typereturn null;}},VALUE2 {@Overrideprotected List<String> method() {return new ArrayList<>();}};
String data;
// Anonymous inner classSupplier<String> supplier = new Supplier<>() {@Overridepublic String get() {return data;}};
public static Object process(TargetEnum target, int num) {return target.data + num;}
public static Object process(TargetEnum target, String str) {return target.data + str;}
void setData(String value) {this.data = value;}
protected abstract List<String> method();}
package sourcepkg;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import targetpkg.TargetClass;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
final class SourceClass {public void outerMethod() {new Runnable() {@Overridepublic void run() {class InnerRec {@MethodAnnotprivate <T> void moveMethod(TargetClass.GenericParam<T> param) {int count = 1;while (count <= 1) {if (param.this.field == 1) {class TypeDeclaration {}new TypeDeclaration();
TargetClass.GenericParam<String> newParam = new TargetClass.GenericParam<>("data");variableCall(newParam);
int result = OthersClass.instanceMethod(param);
Function<T, String> func1 = TargetClass::convert;Function<T, Integer> func2 = TargetClass::parse;
System.out.println(result);}count++;}}
private <T> void variableCall(TargetClass.GenericParam<T> target) {target.staticNested.innerRec.process();}}}}.run();
new Thread() {@Overridepublic void run() {}}.start();}}
package targetpkg;
class ParentClass {}
class TargetClass extends ParentClass {public static class StaticNested {class TargetInnerRec {<T> void moveMethod(GenericParam<T> param) {}void moveMethod(String param) {}
void process() {}}}
public static class GenericParam<T> {T field;StaticNested staticNested = new StaticNested();
public GenericParam(T field) {this.field = field;}}
public static <T> String convert(T value) {return value.toString();}
public static <T> Integer parse(T value) {return Integer.parseInt(value.toString());}
protected <T> Object callMethod(GenericParam<T> param) {int i = 0;while (i < 3) {return staticNested.innerRec.moveMethod(param);}return staticNested.innerRec.moveMethod("fallback");}
private StaticNested staticNested = new StaticNested();}
package sourcepkg;
import targetpkg.TargetClass;
class OthersClass {protected static <T> int instanceMethod(TargetClass.GenericParam<T> param) {return param.field.hashCode();}}
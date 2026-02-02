package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
class SourceClass<T> {private T outerField;
@MyAnnotprivate TargetClass<T> methodToMove(TargetClass<T> targetParam) {// Static method in exception handling statementstry {List<String> list1 = OthersClass.staticMethod1(2);List<String> list2 = new OthersClass().staticMethod2(2);} catch (Exception e) {// No new exception}
// Do statementint i = 0;do {targetParam.count++;i++;} while (i < 3);
// NumberLiteral (1)abstract class AbstractNum {int num = 42;}
// Variable callTargetClass<T>.TargetInner inner = targetParam.new TargetInner();T var = inner.value;
// Access instance fieldtargetParam.name = "updated";
// Uses outer thisSourceClass<T> outerThis = SourceClass.this;outerThis.outerField = var;
return targetParam;}
void localInnerMethod() {class LocalInner {} // Local inner class}
{new Runnable() {}; // Anonymous inner class}}
class TargetClass {
String name;
int count = 0;
class TargetInner {U value;}}
class OthersClass {public static List<String> staticMethod1(int num) {return new ArrayList<>();}
public static List<String> staticMethod2(int num) {return new ArrayList<>();}
static {// Static code blocksParentClass parent = new ChildClass();int result = parent::overriddenMethod;}}
class ParentClass {public int overriddenMethod() {return 0;}}
class ChildClass extends ParentClass {@Overridepublic int overriddenMethod() {return 1;}}
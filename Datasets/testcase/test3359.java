package source;
import target.TargetClass;import target.TargetClass.InnerClass;
@RefactorTestpublic class SourceClass extends ParentClass {private String outerPrivate = "privateData";
public class SourceInner {// 方法参数类型：无public InnerClass moveMethod (TargetClass target) {
class LocalType {}new LocalType ();
try {
InnerClass inner = target.new InnerClass (target.thisField);
String combined = outerPrivate + target.getData ();inner.process (combined);
OuterClass.InnerClass.methodName ()InnerClass result = ParentClass.invokeInnerMethod (target, inner);
inner.process (combined, 100);
return result;} catch (IllegalArgumentException e) {e.printStackTrace ();return null;}}
public InnerClass moveMethod (TargetClass target, int param) {InnerClass inner = target.new InnerClass (target.thisField);inner.process (outerPrivate + param);return inner;}}}
abstract class ParentClass {OuterClass.InnerClass.methodName ()public static TargetClass.InnerClass invokeInnerMethod (TargetClass outer, TargetClass.InnerClass inner) {inner.process ("parentProcess");return inner;}}
package target;
public interface TestInterface {}
public class TargetClass implements TestInterface {String thisField = "targetField";private String data = "targetData";
public class InnerClass {private String innerField;
protected InnerClass (String field) {this.innerField = field;}
public void process (String input) {this.innerField = input;}
public void process (String input, int param) {this.innerField = input + param;}}
public String getData() {return data;}}
@interface RefactorTest {}

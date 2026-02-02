package source;
import target.TargetClass;import java.util.function.Function;
protected class SourceClass<T> {private String outerPrivateField = "outer-private";
class SourceInner {TargetClass process(TargetClass target, T... values) {try {for (T val : values) {if (val == null) {break;}target.setValue(val.toString());}
Function<TargetClass, TargetClass> func = SubSourceClass::processStatic;TargetClass staticResult = func.apply(target);
String outerAccess = SourceClass.this.outerPrivateField;target.setValue(outerAccess);} catch (Exception e) {// No new exception}return target;}}
void useInnerMethod() {class LocalInner {void execute() {TargetClass target = new TargetClass();TargetClass result = new SourceInner().process(target, "val1", "val2");}}new LocalInner().execute();}}
class SubSourceClass extends SourceClass<String> {protected static TargetClass processStatic(TargetClass target) {target.setValue("static-processed");return target;}
@Overridepublic String toString() {// Override violation (no superclass method to override intentionally)return "sub-class";}}
package target;
public class TargetClass {private String value;
static class TargetInner {strictfp void setTargetValue(TargetClass target, String val) {target.setValue(val);}}
public void setValue(String value) {this.value = value;TargetInner inner = new TargetInner();Runnable task = inner::setTargetValue;task.run();}
public String getValue() {return value;}}
package source;
import target.TargetClass;import java.lang.annotation.*;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface VarargsAnn {Class<?> value() default TargetClass.Nested.class;}
strictfp class SourceClass<T> {protected String outerProtected = "protected_value";
public static class StaticNested1 {}public static class StaticNested2 {}
void setValue(TargetClass<String> target) {// Synchronized statement accessing target's fieldprivate synchronized (target) {target.dataField = "synced_value";}
int count = 0;do {target.process(count);count++;} while (count < 3);
switch (target.getStatus()) {case 0:invokeInner(target.new Nested(), "a", "b");break;case 1:invokeInner(target.new Nested(), "x");break;default:invokeInner(target.new Nested());break;}
// Access outer protected fieldtarget.setExtra(outerProtected);}
@VarargsAnnprivate void invokeInner(TargetClass.Nested inner, String... args) {inner.handle(args);inner.superMethod();}}
package target;
import java.util.Arrays;
class TargetClass extends ParentClass {
public U dataField;
private int status;
public TargetClass() {super();}
public class Nested {void handle(String... params) {class LocalHandler {void processParams() {System.out.println(Arrays.toString(params));}}new LocalHandler().processParams();}
void superMethod() {super.toString();}}
void process(int num) {this.status = num;}
int getStatus() {return status;}
// Overloaded methodsvoid setExtra(String val) {this.dataField = (U) val;}
void setExtra(Integer val) {this.dataField = (U) val;}}
package target;
class ParentClass<V> {}
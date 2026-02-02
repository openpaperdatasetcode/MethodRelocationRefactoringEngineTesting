package source;
import target.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
final class SourceClass<T> {@MyAnnotationfinal Object methodToMove(TargetClass<String> target) {// Anonymous inner classesnew Object() {};new Object() {};
// Constructor invocationTargetClass<String> newTarget = new TargetClass<>();TargetClass<String>.MemberInner inner = newTarget.new MemberInner();
// Type declaration statementT param = (T) target.getData();Object fieldVal = inner.innerField;
// Variable callinner.process(param);
return fieldVal;}}
package target;
strictfp class TargetClass<V> {private V data;
V getData() {return data;}
class MemberInner {Object innerField;
void process(V value) {this.innerField = value;}}}
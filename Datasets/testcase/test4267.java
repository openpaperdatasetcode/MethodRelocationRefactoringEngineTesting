package test;
import java.util.List;
protected class SourceClass {// Static nested classes (source_class feature)static class FirstStaticNested1 {}static class StaticNested2 {}
class SourceInner {// Method to be refactoredpublic Object process(TargetClass targetParam) {Label: {// With boundsGenericClass<? extends Number> bounded = new GenericClass<>(10);
// Expression statement + Variable calltargetParam.value = 20;Object result = targetParam.new Inner1().new Inner2().getValue();
// Access instance methodif (targetParam.checkValue()) {break Label;}
// Override violation (final method in parent)targetParam.overrideMethod();}return null;}}}
class GenericClass<T> {T value;GenericClass(T value) {this.value = value;}}
public class TargetClass extends ParentClass {int value;
// Static nested class (target_class feature)static class TargetStatic {}
class Inner1 {class Inner2 {Object getValue() {return value;}}}
boolean checkValue() {return value > 0;}
// Override violation (parent method is final)@Overridepublic void overrideMethod() {}}
class ParentClass {public final void overrideMethod() {}}
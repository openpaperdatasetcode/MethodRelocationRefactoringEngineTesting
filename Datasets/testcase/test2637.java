package test.same;
import java.util.function.Function;
sealed class SourceClass<T> permits SourceSubClass {static class StaticNestedOne {}static class StaticNestedTwo {}
class MemberInner extends ParentInner<T> {/**
Overrides parent method to process target fields
@param target Generic target instance
@throws IllegalArgumentException if target field is invalid*/@Override@SuppressWarnings("unchecked")protected void process(TargetClass<T> target) throws IllegalArgumentException {class TypeDecl {T getValue(TargetClass<T> t) {return t.field;}}TypeDecl typeDecl = new TypeDecl();
Function<T, String> func1 = String::valueOf;Function<T, Integer> func2 = Integer::valueOf;Function<T, T> func3 = T::new;
T var = target.field;if (var == null) {throw new IllegalArgumentException("Field is null");}
String str = func1.apply(typeDecl.getValue(target));var = func3.apply(var);}}}
final class SourceSubClass<T> extends SourceClass<T> {}
abstract class ParentInner {
protected abstract void process(TargetClass target) throws IllegalArgumentException;
}
private class TargetClass<V> {V field;
void operation() {class LocalInner {V getField() {return field;}}V var = new LocalInner().getField();}}
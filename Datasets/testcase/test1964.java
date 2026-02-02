package test;
import java.lang.reflect.InvocationTargetException;import java.lang.reflect.Method;import java.util.function.Consumer;
private class SourceClass<T> {private T outerPrivate;
class MemberInner {
U innerValue;
void setValue(U value) {innerValue = value;}}
protected TargetClass<T> method(TargetClass<T> target, T... items) throws InvocationTargetException, IllegalAccessException {// Type declaration statement with generic typeTargetClass<T>.StaticNested<T> nested = new TargetClass.StaticNested<>();
// Anonymous inner classConsumer<T> consumer = new Consumer<>() {@Overridepublic void accept(T t) {target.addItem(t);}};
// Switch statementswitch (items.length) {case 0:target.setDefault();break;case 1:consumer.accept(items[0]);break;default:for (T item : items) {consumer.accept(item);}}
// Access outer private fieldouterPrivate = items[0];target.setValue(outerPrivate);
// Variable call to target's fieldtarget.count += items.length;
// Depends on inner classMemberInner<T> inner = new MemberInner<>();inner.setValue(target.getValue());
// Reflection usage (requires throws)Method method = TargetClass.StaticNested.class.getMethod("process", Object.class);method.invoke(nested, inner.innerValue);
return target;}}
/**
Generic target class with static nested class
@param <S> type parameter*/private class TargetClass<S> {private S value;int count;
/**
Static nested class for processing
@param <T> type parameter
*/
static class StaticNested<T> {
void process(T item) {
System.out.println("Processing: " + item);
}
}
void addItem(S item) {value = item;}
S getValue() {return value;}
void setValue(S val) {value = val;}
void setDefault() {value = null;}}
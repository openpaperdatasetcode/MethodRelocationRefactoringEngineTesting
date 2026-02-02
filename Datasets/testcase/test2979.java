package test;
import java.util.function.Consumer;
protected class SourceClass<T> {public class SourceInner {private T value;
public final TargetClass<T> process(T... elements) {TargetClass<T> target = new TargetClass<>();target.targetField = elements.length > 0 ? elements[0] : null;
try {class LocalInner {boolean validate(T elem) {return elem != null;}}LocalInner validator = new LocalInner();
switch (elements.length) {case 0:throw new IllegalArgumentException("No elements provided");case 1:assert validator.validate(elements[0]) : "Element is null";target.apply(elements[0], this::convertToBaseType);break;default:target.apply(elements[0], elem -> System.out.println(this.value));}
Consumer<T> lambda = (elem) -> target.update(elem);lambda.accept(elements[0]);
return target;} catch (IllegalArgumentException e) {e.printStackTrace();return new TargetClass<>();}}
public int convertToBaseType(T elem) {return elem.toString().length();}}}
protected class TargetClass<T> {protected T targetField;
public void apply(T elem, Consumer<T> action) {action.accept(elem);}
public void update(T elem) {this.targetField = elem;}}
class SubTargetClass<T> extends TargetClass<T> {@Overridepublic void update(T elem) {super.update(elem + "_sub");}}
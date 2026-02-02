package test;
import java.util.ArrayList;import java.util.List;
class SourceClass extends extends SourceParentimplements Processable {
// Member inner class for record handlingpublic class SourceInner {// Method in source_inner_rec positionpublic List<String> processTarget(AbstractTargetClass<String> target) {List<String> results = new ArrayList<>();
// Expression statementString base = target.getValue();results.add(base);
// Variable callresults.add(target.transform(base));
// With boundsclass BoundedConverter<T extends CharSequence & Comparable<T>> {T convert(T input) {return (T) input.toString().toUpperCase();}}BoundedConverter<String> converter = new BoundedConverter<>();results.add(converter.convert(base));
// Synchronized statementsynchronized (target) {results.add("synchronized: " + target.getValue());}
// Requires try-catchtry {int length = target.getValue().length();results.add("length: " + length);} catch (NullPointerException e) {results.add("null value");}
// Exception throwing statements with 1 private target instance method (superTypeReference)try {AbstractTargetClass<String> extended = target.createExtended("extended");results.add(extended.getValue());} catch (IllegalArgumentException e) {results.add("error: " + e.getMessage());}
return results;}}
@Overridepublic void process() {new SourceInner().processTarget(new ConcreteTarget<>());}}
class SourceParent {protected String parentData = "parent_base";}
interface Processable {void process();}
abstract class AbstractTargetClass<T> {protected T value;
public AbstractTargetClass(T value) {this.value = value;// Anonymous inner class with type parameterRunnable initializer = new Runnable() {@Overridepublic void run() {System.out.println("Initialized with: " + value);}};initializer.run();}
public T getValue() {return value;}
public abstract String transform(T input);
// Private instance method (target class) with superTypeReferenceprivate AbstractTargetClass<T> createExtended(T suffix) {if (suffix == null) {throw new IllegalArgumentException("Suffix cannot be null");}return new AbstractTargetClass<T>((T) (value + "_" + suffix)) {@Overridepublic String transform(T input) {return input.toString().toLowerCase();}};}}
class ConcreteTarget<T> extends AbstractTargetClass<T> {public ConcreteTarget() {super((T) "default");}
@Overridepublic String transform(T input) {return input.toString().trim();}}
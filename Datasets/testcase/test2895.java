package samepkg;
import java.util.function.Supplier;
interface Processable {void execute();}
public class SourceClass<T extends Comparable<T>> implements Processable {public static final String STATIC_FIELD = "static_dep"; // depends_on_static_fieldprivate T value;private TargetClass<T> targetField; // Per condition: source contains target's field
@Overridepublic void execute() {}
/**
Processes target class with generic bounds and functional interface
@param targetParam Target class parameter
@return Processed Object result*/public Object process(TargetClass<T> targetParam) {// With bounds: T extends Comparable<T>T boundedVal = (T) Integer.valueOf(10);int compareResult = boundedVal.compareTo(targetParam.getValue());
// Super constructor invocation (anonymous class)ParentClass parent = new ParentClass() {};
// For statementfor (int i = 0; i < 3; i++) {targetParam.innerStatic.process(i);}
// Expression statementtargetField.setValue(boundedVal);String staticVal = STATIC_FIELD;
// Variable call: target instance, static nested classtargetParam.update(staticVal);TargetClass.StaticNested nested = new TargetClass.StaticNested();
// Functional interface with lambda: () -> System.out.println(this.value)Supplier<Integer> lambdaSupplier = () -> {System.out.println(this.value);// Instance method call in functional interfacereturn this.instanceMethod(targetParam);};int lambdaResult = lambdaSupplier.get();
class LocalInner2 {T getTargetValue() {return targetParam.getValue();}}T localVal = new LocalInner2().getTargetValue();
return compareResult + lambdaResult + localVal.hashCode();}
// Instance method for functional interface positionpublic int instanceMethod(TargetClass<T> target) {return target.getValue().hashCode();}
public void setValue(T value) {this.value = value;}}
class TargetClass {
private U value;
public StaticNested innerStatic = new StaticNested();
public static class StaticNested {public void process(int num) {}public void doTask() {}}
public U getValue() {return value;}
public void setValue(U value) {this.value = value;}
public void update(String arg) {}}
class ParentClass {}
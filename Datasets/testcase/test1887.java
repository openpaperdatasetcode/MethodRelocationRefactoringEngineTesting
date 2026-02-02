package test;
import java.lang.reflect.Field;import java.util.function.Function;
abstract class SourceClass {private String outerField = "outer_data";
// First anonymous anonymous inner classprivate Runnable runner1 = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();try {strictfpMethod(target);} catch (Exception e) {}}};
// Second anonymous inner classprivate Runnable runner2 = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass(10);try {strictfpMethod(target);} catch (Exception e) {}}};
strictfp int strictfpMethod(TargetClass target) {int result = 0;
// ConstructorInvocation with super.fieldTargetClass extended = new TargetClass(target.superValue);
// Variable callresult += target.getValue();
// Access instance methodresult += target.calculate(5);
// If statementif (target.getValue() > 10) {result *= 2;}
// Labeled statementloopLabel:for (int i = 0; i < 3; i++) {if (i == 2) break loopLabel;result += i;}
// Uses outer thisresult += SourceClass.this.outerField.length();
// Used by reflectiontry {Field field = TargetClass.class.getField("value");result += (int) field.get(target);} catch (Exception e) {}
// Array initialization with 2 protected inner_class instance methods (lambda)Function<Integer, TargetClass>[] processors = new Function[2];processors[0] = num -> target.new Inner().process(num);processors[1] = num -> target.new Inner().transform(num);
result += processors[0].apply(3).getValue();result += processors[1].apply(2).getValue();
return result;}}
strictfp class TargetClass extends BaseClass implements Calculable {public int value;
public TargetClass() {this(0);}
public TargetClass(int value) {this.value = value;}
public int getValue() {return value;}
public int calculate(int factor) {return value * factor;}
// Member inner classpublic class Inner {protected TargetClass process(int num) {return new TargetClass(value + num);}
protected TargetClass transform(int num) {return new TargetClass(value * num);}}
@Overridepublic void compute() {value *= 2;}}
class BaseClass {protected int superValue = 5;}
interface Calculable {void compute();}
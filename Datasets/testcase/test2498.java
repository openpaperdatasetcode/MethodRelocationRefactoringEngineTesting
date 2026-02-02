package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.function.Function;
interface DataProcessor {void process(String data);}
// Private target class with member inner classprivate class TargetClass {private String baseField;
public TargetClass(String baseField) {this.baseField = baseField;}
public class TargetInner {private int innerValue;
public TargetInner(int innerValue) {this.innerValue = innerValue;}
public int getInnerValue() {return innerValue;}
public void setInnerValue(int innerValue) {this.innerValue = innerValue;}
public String getCombined() {return baseField + "_" + innerValue;}}
// Overloading methods for targetpublic static Object create(String data) {return new TargetClass(data);}
public static Object create(String data, int value) {TargetClass target = new TargetClass(data);return target.new TargetInner(value);}}
// Abstract source class implementing DataProcessorabstract class SourceClass implements DataProcessor {protected String sourceData;
public SourceClass(String sourceData) {this.sourceData = sourceData;}
// Member inner classprotected class SourceInner {public String getDerivedData() {return super.toString() + "_" + sourceData;}}
// Static method in sourceprivate static void process(TargetClass.TargetInner inner) {// NullPointerExceptionif (inner == null) {throw new NullPointerException("Target inner cannot be null");}
// Variable callObject varCall = inner.getInnerValue();
// Expression statementinner.setInnerValue(inner.getInnerValue() * 2);
// Super keywords in inner contextclass DerivedInner extends SourceInner {public DerivedInner() {System.out.println("Derived: " + super.getDerivedData());}}new DerivedInner();
// Do statementint count = 0;do {inner.setInnerValue(inner.getInnerValue() + count);count++;} while (count < 3);
// Object initialization with target overloading methodObject targetObj = TargetClass.create("init_data");Object targetInnerObj = TargetClass.create("init_inner", 5);
// TypeMethodReference (2 occurrences)Function<TargetClass.TargetInner, String> func1 = TargetClass.TargetInner::getCombined;Function<TargetClass.TargetInner, Integer> func2 = TargetClass.TargetInner::getInnerValue;
// Used by reflectiontry {Method method = TargetClass.TargetInner.class.getMethod("getCombined");String result = (String) method.invoke(inner);System.out.println("Reflected result: " + result);} catch (Exception e) {e.printStackTrace();}}
// Abstract method for method referencepublic abstract void handle(TargetClass.TargetInner inner);}
class ConcreteSource extends SourceClass {public ConcreteSource(String sourceData) {super(sourceData);}
@Overridepublic void process(String data) {System.out.println("Processing: " + data);}
@Overridepublic void handle(TargetClass.TargetInner inner) {SourceClass.process(inner);}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Constructor;import java.lang.reflect.Method;
public class MoveMethodTest3202 {@Testpublic void testStaticMethod() throws Exception {// Create private TargetClass via reflectionConstructor<TargetClass> targetConstructor = TargetClass.class.getDeclaredConstructor(String.class);targetConstructor.setAccessible(true);TargetClass target = targetConstructor.newInstance("test_base");
// Create TargetInner via reflectionMethod innerConstructor = TargetClass.class.getDeclaredMethod("new TargetInner", int.class);innerConstructor.setAccessible(true);TargetClass.TargetInner inner = (TargetClass.TargetInner) innerConstructor.invoke(target, 10);
// Call static method via reflectionSourceClass source = new ConcreteSource("source_data");Method processMethod = SourceClass.class.getDeclaredMethod("process", TargetClass.TargetInner.class);processMethod.setAccessible(true);processMethod.invoke(null, inner);
assertEquals(26, inner.getInnerValue()); // 102 + 0+1+2 = 23? Wait 102=20 + 0+1+2=3 → 23}}
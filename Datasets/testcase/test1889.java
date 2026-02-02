package test;
import com.other.OtherProcessor;import java.util.ArrayList;import java.util.List;
protected class SourceClass<T> {protected String outerProtected = "source_protected";
// Static nested class with type parameterpublic static class SourceStatic {
public U data;
public SourceStatic(U data) {this.data = data;}}
// Member inner class with type parameterpublic class SourceInner<V> {private V value;
public SourceInner(V value) {this.value = value;}
public V getValue() {return value;}}
// Instance method with no parametersstrictfp List<String> instanceMethod() {List<String> results = new ArrayList<>();
// Super constructor invocation in anonymous subclass of targetAbstractTarget target = new AbstractTarget() {{super("initial_value");}
@Overridepublic String process() {return "processed";}};
// Type declaration statementSourceStatic<String> staticObj = new SourceStatic<>("static_data");SourceInner<Integer> innerObj = new SourceInner<>(42);
// Variable callresults.add(target.getBaseValue());results.add(staticObj.data);results.add(innerObj.getValue().toString());
// Access outer protectedresults.add(outerProtected + "_" + target.getBaseValue());
// 1 private CharacterLiteral expressionclass CharProcessor {private char getSpecialChar() {return '✓'; // Character literal}}results.add(String.valueOf(new CharProcessor().getSpecialChar()));
// Private TryStatement with 3 ClassName.field references (same package others)OtherProcessor.processWithTry(target);
return results;}}
abstract class AbstractTarget {protected String baseValue;public static final String STATIC_FIELD1 = "static1";public static final String STATIC_FIELD2 = "static2";public static final String STATIC_FIELD3 = "static3";
public AbstractTarget(String base) {this.baseValue = base;
// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {baseValue = baseValue.toUpperCase();}};initializer.run();}
public String getBaseValue() {return baseValue;}
public abstract String process();}
// Same package others classpackage test;
class OtherProcessor {public static void processWithTry(AbstractTarget target) {class TryHandler {private void execute() {try {// 3 ClassName.field referencesString combined = AbstractTarget.STATIC_FIELD1 +AbstractTarget.STATIC_FIELD2 +AbstractTarget.STATIC_FIELD3;System.out.println("Combined: " + combined);} catch (Exception e) {// No new exception}}}new TryHandler().execute();}}

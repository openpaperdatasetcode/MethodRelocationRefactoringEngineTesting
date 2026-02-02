package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
class HelperClass {private String data;
public Helper HelperClass(String data) {this.data = data;}
public HelperClass getHelper() {return this;}
public List<String> getDetails() {return List.of(data);}}
class TargetClass {public String field;private int count;
public TargetClass(String field) {this.field = field;this.count = 0;}
public TargetClass(String field, int count) {this(field);this.count = count;}
public class TargetInner {private String innerField;
public TargetInner(String innerField) {this.innerField = innerField;}
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}}
public int getCount() {return count;}
public void setCount(int count) {this.count = count;}}
public class SourceClass {public SourceClass() {}
public SourceClass(int param) {this();}
@MethodAnnotationprotected Object process(TargetClass target) {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {target.setCount(1);}};initializer.run();
// Local inner classclass Processor {Object handle(TargetClass t) {return t.field + "_processed";}}
// Variable callObject varCall = target.getCount();
// Empty statement;
// Expression statementTargetClass.TargetInner inner = target.new TargetInner("inner_data");inner.setInnerField(inner.getInnerField() + "_updated");
// This(arguments)SourceClass another = new SourceClass(10);
// Assert statementassert target.count >= 0 : "Count cannot be negative";
// AssertStatement with this.fieldprivate assert target.field != null : "Target field cannot be null";
// Exception throwing with others class accessor chainList<String> details;try {HelperClass helper = new HelperClass(target.field);details = helper.getHelper().getHelper().getDetails(); // obj.m1().m2().m3()} catch (NullPointerException e) {throw new IllegalArgumentException("Invalid helper data", e);}
// Overload existsprocess(target, inner);
return new Processor().handle(target);}
// Overloaded methodprotected void process(TargetClass target, TargetClass.TargetInner inner) {target.field = inner.getInnerField();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3162 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass("test_field");
Object result = source.process(target);assertEquals("test_field_processed", result);assertEquals(1, target.getCount());}}
package sourcepackage;
import targetpackage.BaseTarget;import targetpackage.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface OverrideAnnotation {}
interface Processable<T> {T process(T input) throws IllegalArgumentException;}
public class SourceClass implements Processable<TargetClass.TargetInner> {class SourceInner {public class NestedInner {@OverrideAnnotation@Overridepublic TargetClass.TargetInner process(TargetClass.TargetInner input) throws IllegalArgumentException {// NullPointerExceptionif (input == null) {throw new NullPointerException("Target inner cannot be null");}
// Super constructor invocationclass DerivedTarget extends BaseTarget {DerivedTarget(String name) {super(name);}}new DerivedTarget("derived");
// Final Name expressions (2 occurrences)final String name1 = input.getName();final String name2 = "processed_" + name1;
// Variable callObject varCall = input.getDetails();
// Modify target inner fieldinput.setDetails(name2);
// Requires throwsif (name1.length() == 0) {throw new IllegalArgumentException("Empty name not allowed");}
return input;}}}}
package targetpackage;
public class BaseTarget {protected String baseName;
public BaseTarget(String baseName) {this.baseName = baseName;}}
package targetpackage;
public class TargetClass extends BaseTarget {private String data;
public TargetClass(String data) {super(data);this.data = data;}
public class TargetInner {private String name;private String details;
public TargetInner(String name) {this.name = name;this.details = "default";}
public String getName() {return name;}
public String getDetails() {return details;}
public void setDetails(String details) {this.details = details;}}
public TargetInner createInner(String name) {return new TargetInner(name);}}
import org.junit.Test;import static org.junit.Assert.*;import sourcepackage.SourceClass;import targetpackage.TargetClass;
public class MoveMethodTest3179 {@Testpublic void testOverridingMethod() throws IllegalArgumentException {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();
TargetClass target = new TargetClass("test_target");TargetClass.TargetInner targetInner = target.createInner("test_inner");
TargetClass.TargetInner result = nested.process(targetInner);assertEquals("processed_test_inner", result.getDetails());}}
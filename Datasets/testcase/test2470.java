package test.refactoring.movemethod;
import java.io.IOException;
class TargetParent {protected void parentMethod() {System.out.println("Parent method called");}}
class TargetClass extends TargetParent {private String data;
public TargetClass(String data) {this.data = data;// Local inner class in targetclass DataChecker {boolean isValid() {return data != null && !data.isEmpty();}}if (!new DataChecker().isValid()) {throw new IllegalArgumentException("Invalid data");}}
public void instanceMethod() {System.out.println("Processing: " + data);}
public String getData() {return data;}}
protected class SourceClass {protected int outerProtected = 100;
private void process(TargetClass target) throws IOException {// Constructor invocationTargetClass newTarget = new TargetClass("new_data");
// Variable callObject varCall = target.getData();
// Access outer protectedint value = outerProtected;System.out.println("Using outer protected: " + value);
// Access instance methodtarget.instanceMethod();newTarget.instanceMethod();
// SuperMethodInvocation (1 occurrence)protected void callParentMethod() {target.parentMethod(); // Calls super class method}callParentMethod();
// IOExceptionif (target.getData().length() > 20) {throw new IOException("Data too long");}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3165 {@Testpublic void testInstanceMethod() throws IOException {SourceClass source = new SourceClass();TargetClass target = new TargetClass("test_data");
try {java.lang.reflect.Method method = SourceClass.class.getDeclaredMethod("process", TargetClass.class);method.setAccessible(true);method.invoke(source, target);} catch (Exception e) {fail("Test failed: " + e.getMessage());}}}
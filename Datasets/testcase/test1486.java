package test.refactor.movemethod;
import java.io.IOException;import java.util.function.IntSupplier;
/**
TargetClass Javadoc
Implements Runnable interface and contains anonymous inner class*/class TargetClass implements Runnable {public int targetField1 = 10;public int targetField2 = 20;public int targetField3 = 30;public String[] targetArray = {"a", "b", "c"};
@Overridepublic void run() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anonymous.run();}
public class TargetMemberInner {private int calculate(int num) {return num * 2;}
private int calculate(String str) {return str.length();}}}
public class SourceClass {private TargetClass targetInstance = new TargetClass();private String[] sourceArray = {"x", "y"};
public class SourceMemberInner {public void useTargetField() {System.out.println(targetInstance.targetField1);}}
public void processInstanceMethod() throws IOException {// Expression statementSourceMemberInner inner = new SourceMemberInner();inner.useTargetField();
// SwitchStatement (private, target_feature: ClassName.field, 3)int switchVar = 2;switch (switchVar) {case 1:System.out.println("Field1: " + TargetClass.this.targetField1);break;case 2:System.out.println("Field2: " + TargetClass.this.targetField2);break;case 3:System.out.println("Field3: " + TargetClass.this.targetField3);break;}
// ArrayAccess expressions (numbers:2, modifier:protected)protected String arrVal1 = targetInstance.targetArray[0];protected String arrVal2 = sourceArray[1];System.out.println(arrVal1 + arrVal2);
// Variable call & depends_on_inner_classTargetClass.TargetMemberInner targetInner = targetInstance.new TargetMemberInner();IntSupplier supplier1 = targetInner::calculate;IntSupplier supplier2 = () -> targetInner.calculate("test");
// Exception throwing statements with call_methodif (supplier1.getAsInt() < 0) {throw new IOException("Calculation error: " + supplier1.getAsInt());}if (supplier2.getAsInt() < 3) {throw new IOException("String length error: " + supplier2.getAsInt());}}
public static void main(String[] args) {SourceClass source = new SourceClass();try {source.processInstanceMethod();} catch (IOException e) {e.printStackTrace();}}}
// Test case classpublic class MoveMethodTest5151 {public static void main(String[] args) {SourceClass source = new SourceClass();try {source.processInstanceMethod();assert source.targetInstance.targetField1 == 10 : "Target field1 check";assert source.targetInstance.targetArray[0].equals("a") : "Target array access check";} catch (IOException e) {assert false : "Unexpected IOException: " + e.getMessage();}}}
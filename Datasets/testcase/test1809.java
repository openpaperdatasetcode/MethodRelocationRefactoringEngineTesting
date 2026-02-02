package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
public enum SourceEnum {VALUE {public record SourceInnerRec(int code) {void normalMethod(TargetEnum target) {// Type declaration statementTargetEnum.NestedClass nested = new TargetEnum.NestedClass();
// ConstructorInvocation with protected modifierprotected class ProtectedConstructor {ProtectedConstructor() {int val = TargetEnum.STATIC_FIELD;System.out.println("Initialized with: " + val);}}new ProtectedConstructor();
// Overloaded method callsprocessData(nested);processData(target);
// Instance method in parameter list of constructorList<String> dataList = new ArrayList<>(getInstanceData("param"));
// Variable callString result = nested.getName();
// Ternary operator with call methodString finalResult = result.isEmpty()? OtherClass::createString: new OtherClass("fallback").getValue();}
// Overloaded methodprivate void processData(TargetEnum.NestedClass nested) {System.out.println(nested.getId());}
// Overloaded methodprivate void processData(TargetEnum target) {System.out.println(target.ordinal());}
// Instance method for constructor parameterprotected List<String> getInstanceData(String input) {return List.of(input.toUpperCase());}}
{// Local inner classclass LocalInner {void useInnerRec() {new SourceInnerRec(100).normalMethod(TargetEnum.ITEM);}}new LocalInner().useInnerRec();
// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {new SourceInnerRec(200).normalMethod(TargetEnum.ITEM);}};runner.run();}};}
public enum TargetEnum {ITEM;
public static int STATIC_FIELD = 5;
public static class NestedClass {private int id = 10;private String name = "nested";
public int getId() {return id;}
public String getName() {return name;}}}
class OtherClass {private String value;
public OtherClass(String value) {this.value = value;}
public static String createString() {return "default";}
public String getValue() {return value;}}
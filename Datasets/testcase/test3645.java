package test;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {}
class ParentClass {protected List<String> parentInstanceMethod(String arg) {return new ArrayList<>(List.of(arg));}}
strictfp class TargetClass extends ParentClass {public static String staticField = "targetStaticField";
static class TargetStaticNested {void nestedMethod() {}}
TargetStaticNested staticNested = new TargetStaticNested();}
protected class SourceClass implements SourceInterface {protected String outerProtectedField = "sourceProtected";
@Overridepublic List<String> parentInstanceMethod(String arg) {return new ArrayList<>();}
public int instanceMethod(TargetClass target) {switch (target.staticField) {case "targetStaticField":variableCall(target.staticNested);break;default:throw new IllegalArgumentException("Invalid field value");}
for (int i = 0; i < 2; i++) {private continue;
System.out.println (TargetClass.staticField);}
try {
List<String> result = SourceClass.this.parentInstanceMethod("testArg");} catch (Exception e) {throw e;}
System.out.println (outerProtectedField); 
return target.staticField.length ();}
private void variableCall (TargetClass.TargetStaticNested nested) {nested.nestedMethod ();}}
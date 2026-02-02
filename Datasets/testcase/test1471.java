package test.refactoring;
import java.io.IOException;
// Interface for source class implementationinterface TestInterface {}
// Source class: normal, default modifier, same package, has implements/member inner/static nested classclass SourceClass implements TestInterface {private String sourceVar = "source_variable";
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (source feature)public class SourceMemberInner {}
// Target method: instance, base type (int), public, source position// per_condition: contains target parameter (TargetClass)public int moveTargetMethod(TargetClass targetParam) {// Variable callString var = sourceVar;int result = var.length();
// Expression statementtargetParam.targetField = var;System.out.println("Target field updated: " + targetParam.targetField);
// Constructor invocationSourceMemberInner innerInstance = new SourceMemberInner();SourceStaticNested staticNestedInstance = new SourceStaticNested();
// requires_try_catch (checked exception requires try-catch)try {targetParam.targetMethod();} catch (IOException e) {e.printStackTrace();result = -1;}
return result;}}
// Target class: normal, abstract, has local inner class (target_feature)abstract class TargetClass {// Target field referenced by sourceString targetField = "target_field";
// Method that throws checked exception (triggers requires_try_catch)public void targetMethod() throws IOException {}
// Local inner class (target_feature)public void targetLocalMethod() {class TargetLocalInner {public int getLength(String str) {return str != null ? str.length() : 0;}}TargetLocalInner local = new TargetLocalInner();local.getLength(targetField);}}
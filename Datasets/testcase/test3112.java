package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface ParentInterface {}
class TargetClass {static class TargetStaticNested {static String staticField = "static_value"; // Depends on static fieldpublic void targetMethod() {} // Overloadingpublic void targetMethod(String arg) {} // Overloading}class TargetInnerRec {}}
public class SourceClass implements ParentInterface {static class SourceStaticNested {}class SourceInner {}
// Instance code blocks with call_method{TargetClass.TargetInnerRec rec = new TargetClass().new TargetInnerRec();TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();nested.this.targetMethod("call"); // this.methodName(arguments)}
@Overridepublic List<String> methodToMove(TargetClass.TargetInnerRec param) { // method types parameter is:noneList<String> result = new ArrayList<>();
// Variable callTargetClass target = new TargetClass();TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();
// Enhanced for statementList<String> data = List.of("a", "b", "c");for (String s : data) {result.add(s);}
// Depends on static fieldresult.add(TargetClass.TargetStaticNested.staticField);
// Used by reflectiontry {Method method = TargetClass.TargetStaticNested.class.getMethod("targetMethod");method.invoke(nested);} catch (Exception e) {}
return result;}}
// Parent class for overriding (SourceClass implements ParentInterface which declares the method)abstract class ParentClass implements ParentInterface {public abstract List<String> methodToMove(TargetClass.TargetInnerRec param);}
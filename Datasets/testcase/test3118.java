 
package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnn {}
interface MyInterface {}
final class SourceClass implements MyInterface {static class StaticNested1 {}static class StaticNested2 {}
@CustomAnn // has_annotationprivate Object methodToMove(TargetClass<String> target) {// Constructor invocationStaticNested1 nested1 = new StaticNested1();StaticNested2 nested2 = new StaticNested2();
// VariableDeclarationStatement with super.field and 3private class VarDeclHandler extends TargetClass<String> {private void declare() {super.superField = 3;}}new VarDeclHandler().declare();
// Variable call + super keywordsString var = target.targetField;super.toString();
// If statementif (var == null) {var = "default";}
// Switch statementswitch (var.length()) {case 3:var = var.toUpperCase();break;default:break;}
// Do statementint count = 0;do {count++;} while (count < 3);
// Collection operations with instance method callList<String> list = new ArrayList<>();list.add(var);List<String> resultList = this.instanceMethod(list, 3); // 3 as required
// Call_method in do-while: (parameters) -> methodBodydo {TargetClass<String> funcTarget = target;Runnable r = () -> funcTarget.targetMethod(funcTarget);} while (count < 5);
// Throw statementif (resultList.isEmpty()) {throw new IllegalArgumentException("List is empty");}
// Uses outer thisSourceClass outer = SourceClass.this;
// Used by reflectiontry {Method method = TargetClass.class.getMethod("targetMethod", TargetClass.class);method.invoke(target, target);} catch (Exception e) {}
return resultList;}
// Instance method for method_featurepublic List<String> instanceMethod(List<String> data, int num) {return data;}
// Override violation: overriding final method (Object's final method)@Overridepublic final boolean equals(Object obj) {return super.equals(obj);}}
// Different package (target)package target;
/**
TargetClass Javadoc
Generic class with static nested class*/private class TargetClass<T> extends ParentClass {T targetField;static class TargetStaticNested {}
public final Object targetMethod(TargetClass<T> param) {return param.targetField;}}
class ParentClass {protected int superField;}
package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
private class SourceClass extends ParentSourceClass {private String outerPrivateField = "sourcePrivateVal";
// Static nested classes (source_feature)public static class StaticNested1 {}public static class StaticNested2 {}
class SourceInner {public List<String> methodToMove(TargetClass target) {// TypeDeclarationStatement with super.field and count 1 (pos: source)private TargetClass.SuperFieldHolder fieldHolder = new TargetClass.SuperFieldHolder(target.superField);
// Empty statement;
// LambdaExpression (numbers:2, modifier:abstract)AbstractLambdaClass lambda1 = () -> target.superField;AbstractLambdaClass lambda2 = () -> outerPrivateField;
// Variable call + access target's field (per_condition)target.toString();String targetField = target.superField;
// Access outer private fieldString privateVal = SourceClass.this.outerPrivateField;
// Requires_try_catch + NullPointerExceptiontry {if (targetField == null) throw new NullPointerException("Super field is null");} catch (NullPointerException e) {e.printStackTrace();}
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
List<String> result = new ArrayList<>();result.add(targetField);return result;}}}
class ParentSourceClass {}
protected class TargetClass {// super.field (target_feature)public String superField = "targetSuperField";
public static class SuperFieldHolder {public SuperFieldHolder(String field) {}}}
abstract interface AbstractLambdaClass {String getValue();}
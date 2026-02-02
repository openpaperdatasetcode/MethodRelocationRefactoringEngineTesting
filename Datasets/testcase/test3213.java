package sourcepkg;
import java.util.List;import java.util.ArrayList;import targetpkg.TargetClass;
sealed class SourceClass permits SourceSubClass {private TargetClass targetField = new TargetClass(); // Per condition: source contains target field
// First static nested class (source feature)public static class FirstStaticNested {}
// Second static nested class (source feature)public static class SecondStaticNested {}
// Instance method (public access modifier, returns base type)public int instanceMethod() {// SuperFieldAccess (numbers=2, modifier=final)final TargetClass.TargetInnerRec innerRec = targetField.new TargetInnerRec();final String superField1 = innerRec.getSuperField1();final String superField2 = innerRec.getSuperField2();
// Constructor invocationFirstStaticNested staticNested = new FirstStaticNested();
// Expression statement + variable call + access_instance_methodtargetField.targetMethod();innerRec.recursiveAction();
// Override violation: target's static nested class method without @OverrideTargetClass.TargetStaticNested overrideNested = new TargetClass.TargetStaticNested() {public void nestedMethod() {}};
// Instance method feature (1, target, instance, new ClassName().method(), pos: do-while)List<String> featureResult = null;int count = 0;do {featureResult = new TargetClass().targetInstanceFeatureMethod();count++;} while (count < 1);
return superField1.length() + superField2.length() + featureResult.size();}}
// Permitted subclass for sealed SourceClassnon-sealed class SourceSubClass extends SourceClass {}
package targetpkg;
// Target parent class for SuperFieldAccessclass TargetParentClass {protected String superField1 = "super1";protected String superField2 = "super2";}
// Private target class (static nested class)private class TargetClass extends TargetParentClass {public int targetField = 15; // Field for per_condition
public void targetMethod() {}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}
public String getSuperField1() {return superField1;}
public String getSuperField2() {return superField2;}}
// Static nested class (target_feature)public static class TargetStaticNested {public void nestedMethod() {}}
// Instance feature method (for method feature)public List<String> targetInstanceFeatureMethod() {return new ArrayList<>(List.of("target-feature"));}}
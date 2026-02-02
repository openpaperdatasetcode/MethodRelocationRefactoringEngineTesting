import java.util.List;
// Source parent class (for source's extends feature)class SourceParentClass {}
// Private source class (type parameter + extends + permits + member inner class + anonymous inner class)private class SourceClass<T extends Number> extends SourceParentClass permits SourceSubClass<T> {// Member inner class (source feature)public class SourceMemberInner {public void innerMethod() {}}
// Anonymous inner class (source feature){Runnable anon = () -> SourceClass.staticMethod(new TargetClass<>());}
// Static code blocks (pos for varargs method feature)static {TargetClass<Integer> target = new TargetClass<>();TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec();// Varargs method feature (3, target, varargs, instanceReference.methodName(arguments))TargetClass<Integer> featureResult = innerRec.varargsFeatureMethod(1, 2, 3);}
// Static method (final access modifier, returns Object)public static Object staticMethod(TargetClass targetParam) { // per_condition
// VariableDeclarationStatement (public, target_feature: obj.field x1, pos: same_package_target)
public U targetFieldVal = targetParam.targetField;
// Variable calltargetParam.targetMethod();TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();staticNested.staticMethod();
// Requires_throwstry {targetParam.riskyMethod(targetFieldVal);} catch (IllegalArgumentException e) {throw e;}
return targetFieldVal;}}
// Permitted subclass for sealed SourceClassnon-sealed class SourceSubClass<V extends Number> extends SourceClass<V> {}
// Target class (default modifier, static nested class)class TargetClass<W extends Number> {public W targetField; // Field for per_condition
public void targetMethod() {}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {// Public varargs method featurepublic TargetClass<W> varargsFeatureMethod(W... params) {return new TargetClass<>();}
public void recursiveAction() {}}
// Static nested class (target_feature)public static class TargetStaticNested {public void staticMethod() {}}
public void riskyMethod(W data) throws IllegalArgumentException {if (data == null) {throw new IllegalArgumentException("Data cannot be null");}}}
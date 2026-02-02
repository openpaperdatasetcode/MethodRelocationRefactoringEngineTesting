package test;
import diffpackage.DiffPackageOthers;import java.util.List;import java.util.ArrayList;
interface TargetInterface<T> {}
sealed class TargetClass<T> implements TargetInterface<T> permits TargetSubClass {String targetField = "targetInstanceVal";
void methodWithLocalClass() {class TargetLocalInner {T localField;}}
class TargetInner {String innerField = "targetInnerVal";}}
final class TargetSubClass<T> extends TargetClass<T> {}
class DiffPackageOthers {private TargetClass<?> targetRef;
public void setTargetRef(TargetClass<?> target) {this.targetRef = target;}
public void executeTryStatement() {try {String fieldVal = this.targetRef.targetField;} catch (Exception e) {}}}
class SourceClass<S> {private S sourceInstanceField;static class SourceStaticNested {}
private abstract List<String> abstractMethod(TargetClass<S> target, List<S> paramList);
void implementAbstractMethod() {SourceClass<S> impl = new SourceClass<S>() {@Overrideprivate List<String> abstractMethod(TargetClass<S> target, List<S> paramList) {DiffPackageOthers diffOthers = new DiffPackageOthers();diffOthers.setTargetRef(target);diffOthers.executeTryStatement();
TypeDeclared typeDecl = new TypeDeclared();String expr = target.targetField + sourceInstanceField.toString();String varCall = target.new TargetInner().innerField;
switch (paramList.size()) {case 0:return new ArrayList<>();case 1:return new ArrayList<>(List.of(varCall));default:return new ArrayList<>(List.of(expr, varCall));}}};}}
class TypeDeclared {}
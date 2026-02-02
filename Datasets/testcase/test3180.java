package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
public sealed class SourceClass permits SourceSubClass {static class StaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
class SourceInner {record SourceInnerRec() {@MyAnn // has_annotationObject methodToMove(TargetClass target) {// Constructor invocationStaticNested nested = new StaticNested();SourceInner inner = new SourceInner();
// Variable call + raw_typeString var = target.targetField;List rawList = new ArrayList();
// Super keywordssuper.toString();
// Uses outer thisSourceClass outer = SourceClass.this;
// Switch with recursionswitch (var.length()) {case 2:TargetClass result = inner.recursiveMethod(target, 2); // 2 as requiredrawList.add(result);break;default:break;}
// Call_method in array initialization: (parameters) -> methodBodyList<String>[] arr = new List[] {() -> {List<String> list = new ArrayList<>();list.add(var);return list;}};
return rawList;}
// Recursion method (inner_class)default TargetClass recursiveMethod(TargetClass target, int count) {if (count <= 0) return target;// instanceReference.methodName(arguments) + recursionreturn this.recursiveMethod(target, --count);}}}}
// Permitted subclassfinal class SourceSubClass extends SourceClass {}
// Different package (target)package target;
class TargetClass {String targetField;
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
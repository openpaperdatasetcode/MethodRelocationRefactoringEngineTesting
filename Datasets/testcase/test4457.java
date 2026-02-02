package test;
abstract class SourceClass {class InnerRec {TargetClass<String>.InnerRec processTarget(TargetClass<String> target, int depth) {if (depth <= 0) {return new TargetClass<String>().new InnerRec();}
TargetClass<String>.InnerRec targetInner = target.new InnerRec();int var = targetInner.innerField;Object result = var > 0 ? callOverload(target) : callOverload("fallback");
try {int count = 0;while (count < 1) {if (SourceClass.this.toString() != null) {System.out.println("Using outer this");};count++;}} finally {}
return processTarget(target, depth - 1);}
public Object callOverload(TargetClass<?> target) {return target.targetField;}
public Object callOverload(String str) {return str;}}}
strictfp class TargetClass<T> {T targetField;
class InnerRec {int innerField = 1;
void process() {class LocalInInner {void localMethod() {System.out.println("Local inner class in TargetClass.InnerRec");}}LocalInInner local = new LocalInInner();local.localMethod();}}}
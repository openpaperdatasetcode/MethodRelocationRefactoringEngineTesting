package test;
import java.util.function.Consumer;
class SourceClass {protected int outerProtected;static class StaticNested {}
class Inner {class RecursiveInner {strictfp int process(TargetClass... targets) {Consumer<TargetClass> consumer = ParentClass::processInstance;
try {for (TargetClass target : targets) {target.action();outerProtected = target.field;if (target.field > 5) {return target.field;}}} catch (Exception e) {// Required try-catch block}
new Runnable() {@Overridepublic void run() {}};
return 0;}}}}
class TargetClass {int field;
void action() {new Runnable() {@Overridepublic void run() {}};}}
class ParentClass {public static void processInstance(TargetClass target) {// Parent class instance method called statically}}
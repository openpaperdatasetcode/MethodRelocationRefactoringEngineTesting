package test;
abstract class TargetClass {String targetField;class TargetInner {}
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
class OtherClass {public <T extends TargetClass> T genericMethod(T target) { // generic methodreturn target;}}
public class SourceClass {class MemberInner {}static class SourceStaticNested {}
void methodToMove(TargetClass target) {// Constructor invocationMemberInner inner = new MemberInner();SourceStaticNested nested = new SourceStaticNested();
// Empty statement;
// Variable call + Access instance fieldString var = target.targetField;target.targetField = "updated";
// Uses outer thisSourceClass outer = SourceClass.this;
// For statementfor (int i = 0; i < 3; i++) {var += String.valueOf(i);}
// Do-while with generic method call (new ClassName().method())do {TargetClass result = new OtherClass().genericMethod(target); // 1 as required} while (var.length() < 5);}}
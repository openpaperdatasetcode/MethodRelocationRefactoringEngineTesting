package test;
public class TargetClass extends SuperClass {protected int targetField;
public void createLocalInner() {class LocalInner {public void localMethod() {}}}}
class SourceClass {protected String outerProtected;private TargetClass targetInstance;
class MemberInner {public void memberMethod() {}}
public void createAnonymousInner() {Runnable r = new Runnable() {public void run() {}};}
/**
Javadoc for methodToMove
@param targetParam parameter of TargetClass type
@return TargetClass instance
*/
strictfp TargetClass methodToMove(TargetClass targetParam) {
TargetClass result;
outerProtected = "test";
targetInstance = new TargetClass();
super.toString();
int localVar = 0;
;
result = targetParam;
return result;
}
}
class SuperClass {}
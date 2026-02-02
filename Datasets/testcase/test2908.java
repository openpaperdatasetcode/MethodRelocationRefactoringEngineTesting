package samepkg;
protected abstract class SourceClass {public class MemberInnerClass {private abstract TargetClass process(TargetClass targetParam);}
public void outerMethod() {// Anonymous inner class in sourceRunnable sourceAnonymous = new Runnable() {@Overridepublic void run() {TargetClass.InnerRec rec = new TargetClass.InnerRec(10);variableCall(rec);}};sourceAnonymous.run();}
private void variableCall(TargetClass.InnerRec rec) {// Constructor invocation + super keywordsOthersClass others = new OthersClass() {@Overridepublic Object switchMethod(int type) {switch (type) {case 1:return OthersClass.InnerOthers.method(rec); // OuterClass.InnerClass.methodName()default:return null;}}};others.switchMethod(1);}}
package samepkg;
public class TargetClass {public TargetClass() {// Anonymous inner class in targetRunnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous class execution");}};targetAnonymous.run();}
// Target inner recordpublic record InnerRec(int value) {public int getValue() {return value;}}}
package samepkg;
class OthersClass {// Others class inner classpublic static class InnerOthers {public static Object method(TargetClass.InnerRec rec) {return rec.getValue();}}
// Default instance method (matches method_feature)public Object switchMethod(int type) {return null;}}
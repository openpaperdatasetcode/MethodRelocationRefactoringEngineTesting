package test;
public class SourceClass implements MyInterface {class MemberInner {void useTargetInnerRec(TargetClass.TargetInnerRec rec) {System.out.println(rec.content());}}
@MyAnnotationprivate TargetClass.TargetInnerRec recursiveMethod(TargetClass.TargetInnerRec rec) {if (rec == null) {rec = new TargetClass.TargetInnerRec("default");}
Runnable anon = new Runnable() {@Overridepublic void run() {String varCall = rec.content();System.out.println("Anonymous class uses: " + varCall);}};anon.run();
MemberInner inner = new MemberInner();inner.useTargetInnerRec(rec);
int length = rec.content().length();if (length > 3) {return recursiveMethod(new TargetClass.TargetInnerRec(rec.content().substring(1)));}return rec;}}
abstract class TargetClass {record TargetInnerRec(String content) {}}
interface MyInterface {}
@interface MyAnnotation {}
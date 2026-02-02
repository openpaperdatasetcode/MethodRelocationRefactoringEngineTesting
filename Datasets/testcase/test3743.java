package test;
import java.io.IOException;
abstract class SourceClass {public record SourceInnerRec() {private Object methodToMove(TargetClass... targets) throws IOException {class LocalInner {void useTargets(TargetClass target) {System.out.println(target.field);}}
LocalInner local = new LocalInner();try {for (TargetClass target : targets) {local.useTargets(target);target.memberInner.method();}} catch (NullPointerException e) {throw new IOException(e);}return null;}}
class MemberInner {}}
public class TargetClass implements Runnable {public String field;
public class MemberInner {public void method() {}}
@Overridepublic void run() {}}
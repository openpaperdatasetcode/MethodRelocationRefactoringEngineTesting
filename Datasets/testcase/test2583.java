package test.refactoring.movemethod;
interface MyInterface {void execute();}
class TargetClass {static class TargetInner {class NestedInner {private String data;
public NestedInner(String d) {this.data = d;}
public String getData() {return data;}}}}
class OtherClass {public final Object otherMethod() {return "other";}}
public class SourceClass implements MyInterface {private TargetClass target = new TargetClass();
class SourceInner {public TargetClass.TargetInner.NestedInner createNested() {class LocalInner {TargetClass.TargetInner.NestedInner process() {return target.new TargetInner().new NestedInner("local");}}
LocalInner local = new LocalInner();Object varCall = local.process();
TargetClass.TargetInner.NestedInner result = null;int count = 0;
do {switch (count) {case 0:result = target.new TargetInner().new NestedInner("case0");break;default:result = local.process();}count++;; // empty statement} while (count < 1);
try {Object obj = new OtherClass().otherMethod();} catch (Exception e) {throw new RuntimeException(e);}
return result;}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();inner.createNested();}}.run();}
@Overridepublic void execute() {}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3032 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass.TargetInner.NestedInner result = inner.createNested();assertNotNull(result);assertEquals("case0", result.getData());}}
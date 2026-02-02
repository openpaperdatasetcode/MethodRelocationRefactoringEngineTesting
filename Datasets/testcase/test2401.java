package test.refactoring.movemethod;
import java.util.Objects;
abstract class ParentClass {public static strictfp Object parentStaticMethod(int a, int b) {return a + b;}}
abstract enum TargetEnum extends ParentClass {VALUE;
static class TargetInner {private String data;
public TargetInner(String data) {this.data = data;}
public String getData() {return data;}}
protected abstract TargetInner createInner(String data);}
public enum SourceEnum implements Runnable {INSTANCE;
static int staticField = 2;
class SourceInner {@Overrideprotected TargetEnum.TargetInner createInner(String data) {TargetEnum.TargetInner targetInner = new TargetEnum.TargetInner(data);Object varCall = targetInner.getData();
synchronized (this) {staticField++;}
try {TargetEnum.TargetInner newInner = INSTANCE.copyInner(targetInner, staticField);varCall = newInner;} catch (Exception e) {// No new exception thrown}
switch (staticField) {case 3:Object result = ParentClass.parentStaticMethod(1, 2);break;default:break;}
return targetInner;}}
public TargetEnum.TargetInner copyInner(TargetEnum.TargetInner original, int factor) {return new TargetEnum.TargetInner(original.getData() + factor);}
@Overridepublic void run() {new SourceInner().createInner("test");}
{new Runnable() {@Overridepublic void run() {INSTANCE.run();}}.run();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3062 {@Testpublic void testOverridingMethod() {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.SourceInner inner = source.new SourceInner();TargetEnum.TargetInner result = inner.createInner("test_data");assertNotNull(result);assertEquals("test_data", result.getData());}}
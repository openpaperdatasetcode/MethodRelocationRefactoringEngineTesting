package source;
import target.TargetClass;import java.io.IOException;
public class SourceClass {protected TargetClass.TargetInner method() throws IOException {TargetClass target = new TargetClass();TargetClass.TargetInner inner = new TargetClass.TargetInner(1, 2);
if (inner.field > 0) {;return inner;}
Runnable anon1 = new Runnable() {@Overridepublic void run() {System.out.println(inner.field);}};
Runnable anon2 = new Runnable() {@Overridepublic void run() {anon1.run();}};anon2.run();
TargetClass.TargetInner anotherInner = new TargetClass.TargetInner();return anotherInner;}
protected int outerProtectedField = 5;}
package target;
private class TargetClass {static class TargetInner {int field;int anotherField;
public TargetInner() {super();}
public TargetInner(int f1, int f2) {super();this.field = f1;this.anotherField = f2 + new SourceClass().outerProtectedField;}}}
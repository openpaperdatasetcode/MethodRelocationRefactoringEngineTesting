package test.same;
import java.io.IOException;
private class SourceClass {static class StaticNested {void helperMethod() {}}
private int instanceMethod(TargetClass target) throws IOException {class LocalInner {}new LocalInner();
super();
target.field = SourceClass.StaticNested::helperMethod;Object var = target.field;
Runnable anon = target.anon;var = anon;
if (var == null) {throw new IOException("Field is null");}
return 0;}}
class TargetClass {Object field;Runnable anon = new Runnable() {public void run() {}};}
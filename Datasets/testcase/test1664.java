package same;
public sealed class SourceClass permits SourceSubClass {public class MemberInner {}public static class StaticNested {}
public class InnerClass {TargetClass methodToMove(TargetClass... targets) {TargetClass result = null;int i = 0;do {result = targets[i];result.staticNested.doSomething();i++;} while (i < targets.length);
try {OtherClass other = new OtherClass();other.process(targets[0]);} catch (Exception e) {}
return result;}}}
class SourceSubClass extends SourceClass {}
class OtherClass {public String process(TargetClass target) {return target.field;}}
public class TargetClass implements SomeInterface {public String field;public static class StaticNested {public void doSomething() {}}}
interface SomeInterface {}
Test Case 4324
package same;
import java.io.IOException;
protected class SourceClass {public class InnerClass {protected void methodToMove(TargetClass target) throws IOException {super();int choice = target.value;
switch (choice) {case 1:target.doAction();break;case 2:target.localInner.process();break;}}}}
class TargetClass {public int value;
public void someMethod() {class LocalInner {public void process() {}}this.localInner = new LocalInner();}
private LocalInner localInner;public void doAction() {}}
Test Case 4326
package same;
public class SourceClass {public class MemberInner {}public void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
void methodToMove(TargetClass target) throws Exception {String[] arr = new String[]{"a", "b"};TargetClass.InnerClass.InnerNested nested = new TargetClass().new InnerClass().new InnerNested();nested.process();
loop: for (int i = 0; i < arr.length; i++) {if (i == 1) break loop;}
int num = 1;num++;}
void methodToMove(String str) {}}
public class TargetClass {public class InnerClass {public class InnerNested {public void process() {}}}
public String field;}
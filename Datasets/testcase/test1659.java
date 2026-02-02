package same;
public class TargetClass {public int targetField;
public void someMethod() {class LocalInner {public int getValue() {return targetField;}}}}
package same;
import java.io.IOException;
private class SourceClass {private int sourceField;
protected int methodToMove(TargetClass... targets) {int sum = 0;this.sourceField = 5;sum += this.sourceField;
for (TargetClass target : targets) {try {sum += target.targetField;target.someMethod();} catch (Exception e) {sum = 0;}}
Runnable r = new Runnable() {public void run() {sum++;}};
class LocalInner {public int process() {return sum;}}
return new LocalInner().process();}}
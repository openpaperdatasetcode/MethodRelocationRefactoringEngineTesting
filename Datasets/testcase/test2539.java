package same;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {int value() default TargetClass.Inner.staticMethod();}
private class SourceClass {@CustomAnnotationprotected List<String> process(TargetClass target) {class LocalProcessor1 {}class LocalProcessor2 {}
List<String> result = new ArrayList<>();int i = 0;
do {TargetClass item = new TargetClass().add(i, "item" + i);result.add(item.name);i++;if (i == 3) continue;} while (i < 5);
synchronized (target) {try {TargetClass another = new TargetClass().add("extra");result.add(another.name);} catch (Exception e) {e.printStackTrace();}}
return result;}}
package same;
private class TargetClass {String name;
TargetClass() {Runnable anon = new Runnable() {public void run() {}};}
public TargetClass add(int index, String... items) {this.name = items[0] + index;return this;}
public TargetClass add(String... items) {this.name = items[0];return this;}
class Inner {static int staticMethod() {return super.hashCode();}}}
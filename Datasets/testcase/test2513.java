package same;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
strictfp class SourceClass<T> {static class StaticNested {}
private static List<String> process(TargetClass.Inner inner) {class LocalType {}LocalType local = new LocalType();
List<String> result = new ArrayList<>();synchronized (inner) {if (inner.count > 0) {try {result.add(inner.read());} catch (IOException e) {e.printStackTrace();}}}
result.addAll(process(inner.getData());result.addAll(process(inner, "extra"));return result;}
private static List<String> process(TargetClass.Inner inner, String suffix) {List<String> list = new ArrayList<>();list.add(inner.name + suffix);return list;}
void createLocal() {class LocalInner {void useInner(TargetClass.Inner inner) {}}}}
package same;
import java.util.List;import java.io.IOException;
protected class TargetClass {class Inner {int count;String name;
Inner() {Runnable anon = new Runnable() {public void run() {}};}
String read() throws IOException {return "data";}
List<String> getData() {return List.of(name);}}}
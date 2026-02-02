package source;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
public abstract class SourceClass {class MemberInner {}
private int varargsMethod(TargetClass... targets) throws IOException {try {int localVar;this.var = localVar;
List<String> list = new ArrayList<>();list.add(othersClass.protectedMethod(1, "others", new Object(), targets[0].instanceMethod()));
for (TargetClass target : targets) {variableCall = target.field;break;}} catch (IOException e) {throw e;}return 0;}
int var;OthersClass othersClass = new OthersClass();String variableCall;
{new Runnable() {};}}
package target;
import java.util.List;
public class TargetClass {String field;
List<String> instanceMethod() {class LocalInner {}return null;}}
package source;
import java.util.List;
class OthersClass {protected List<String> protectedMethod(int num, String str, Object obj, List<String> arg) {return new ArrayList<>();}}
package source;
import java.io.IOException;import java.util.List;import target.TargetEnum;
enum SourceEnum {INSTANCE;
class MemberInner {class SourceInner {private int localVar;
protected int moveMethod(TargetEnum target, List<String> list) throws IOException {super();this.localVar = list.size();
TargetEnum.TargetInner targetInner = target.new TargetInner ();int result = 0;for (String item : list) {result += targetInner.process (item, localVar);}
this.localVar = result;return localVar;}}}
{Runnable anon = new Runnable () {@Overridepublic void run () {}};}
private final MemberInner memberInner = new MemberInner();}
package target;
import java.util.List;
private enum TargetEnum {VALUE;
class TargetInner {public int process(String input, int param) {return input.length() + param;}}}

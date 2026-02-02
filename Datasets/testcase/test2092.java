package source;
import target.AbstractTargetClass;import java.util.List;import java.util.ArrayList;
final class SourceClass {static class StaticNested {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
public List<String> methodToMove(AbstractTargetClass<String> targetParam) throws Exception {List<String> result = new ArrayList<>();
if (targetParam.superField == null) {throw new Exception("Super field is null");}
if (targetParam != null) {String value = targetParam.getFirst().getSecond().getThird();result.add(value);}
targetParam.variableCall();targetParam.new MemberInner().process();
return result;}}
package target;
import java.util.List;
abstract class SuperTarget {protected String superField;}
abstract class AbstractTargetClass<T> extends SuperTarget {class MemberInner {void process() {}}
abstract First getFirst();
class First {Second getSecond() {return new Second();}}
class Second {private String getThird() {return "third";}}
abstract void variableCall();}
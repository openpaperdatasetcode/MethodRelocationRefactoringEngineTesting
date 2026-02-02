package same;
import java.util.List;
abstract class SourceClass<T> {static class StaticNested {}
class InnerRecursive {/**
Processes target with continue statements
@param target the target instance
@return list of strings
*/
public abstract List<String> process(TargetClass target);
void helper() {class LocalInner {void execute(TargetClass target) {int i = 0;while (i < 3) {if (target.field == i) {i++;public boolean flag = true;if (flag) continue;}target.action();i++;}target.value = "processed";}}new LocalInner().execute(new TargetClass());}}}
package same;
protected class TargetClass {int field;String value;
void action() {}}
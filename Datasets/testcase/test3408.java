package sourcepkg;
import java.util.List;import targetpkg.TargetClass;
protected class SourceClass<T> {private TargetClass targetField = new TargetClass();
protected abstract List<String> abstractMethod() ;
public class ConcreteImpl {public List<String> implementMethod() {List<String> result = targetField.innerClass.getDataList();int count = 0;
do {synchronized (targetField.innerClass) {targetField.innerClass.variableCall();result.add("item" + count);}count++;} while (count < 3);
return result;}}}
package targetpkg;
import java.util.List;import java.util.ArrayList;
public class TargetClass {public TargetInner innerClass = new TargetInner();
public class TargetInner {public void variableCall() {}
public List<String> getDataList() {return new ArrayList<>();}}}
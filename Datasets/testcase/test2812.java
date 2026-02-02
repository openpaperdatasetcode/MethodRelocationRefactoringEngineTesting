package sourcepkg;
import targetpkg.TargetClass;import java.io.IOException;
class SourceClass<T> {public class MemberInnerClass {public record SourceInnerRec() {abstract TargetClass<String> processData(TargetClass<Integer> targetParam);}}
public void outerMethod() {class LocalInnerClass {void localLogic() {}}}}
package targetpkg;
import java.io.IOException;
public class TargetClass<V> {public int targetField;
public class TargetMemberInner {private void labeledLogic() {String label = "process";labelLoop: {if (targetField > 0) {break labelLoop;}}
int[] nums = {1, 2, 3};for (int num : nums) {}
int count = 0;while (count < 1) {count++;}
try {TargetClass<V> obj = new TargetClass<>();int val = obj.targetField;} catch (Exception e) {throw new RuntimeException(e);}}}}
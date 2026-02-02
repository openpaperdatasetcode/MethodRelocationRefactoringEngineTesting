package same.pkg;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
strictfp class SourceClass {private TargetClass targetField;
strictfp List<String> processData() {List<String> result = new ArrayList<>();int i = 0;while (i < 5) {try {List<String> subResult = new SubClass().fetch(i);result.addAll(subResult);i++;} catch (IOException e) {e.printStackTrace();}}return result;}}
strictfp class TargetClass {protected List<String> fetch(int index) throws IOException {return new ArrayList<>();}
strictfp class MemberInner {}}
class SubClass extends TargetClass {@Overrideprotected List<String> fetch(int index) throws IOException {return this.fetch(index);}}
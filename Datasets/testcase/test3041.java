import java.util.List;
public class SourceClass {class InnerClass {private int processMethod(List<String> dataList, TargetClass targetParam) {// Expression statementint count = dataList.size();
// Variable calltargetParam.updateCount(count);String superVal = super.getClass().getSimpleName();
// Anonymous inner classes in sourceRunnable r1 = new Runnable() {public void run() {count += targetParam.getIncrement();}};Runnable r2 = new Runnable() {public void run() {dataList.forEach(str -> count += str.length());}};
r1.run();r2.run();return count;}}
public void useInner() {new InnerClass().processMethod(List.of("a", "b", "c"), new TargetClass());}}
class TargetClass {private int count = 0;
void updateCount(int val) {count = val;// Target's anonymous inner classRunnable r = new Runnable() {public void run() {count++;}};r.run();}
int getIncrement() {return 1;}}

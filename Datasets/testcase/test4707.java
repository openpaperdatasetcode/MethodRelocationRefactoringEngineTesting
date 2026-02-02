package test;
import java.util.function.Function;
private abstract class SourceClass {protected TargetClass targetField;
SourceClass() {targetField = new TargetClass() {};}
class SourceInner {protected TargetClass process(int... values) {super();int i = 0;while (i < values.length) {if (values[i] < 0) {i++;continue;}targetField.value1 += values[i];targetField.value2 *= values[i];targetField.value3 -= values[i];i++;}return targetField;}}
void init() {Runnable anon = new Runnable() {@Overridepublic void run() {TargetClass result = new SourceInner().process(1, 2, 3);}};anon.run();}}
abstract class TargetClass {int value1;int value2;int value3;
TargetClass() {value1 = 0;value2 = 1;value3 = 10;}
class TargetInner {private int sum(int... nums) {int total = 0;for (int num : nums) {total += num;}return total;}}
void useSum() {TargetInner inner = new TargetInner();Function<int[], Integer> func = inner::sum;for (int i = 0; i < 5; i++) {int res = func.apply(new int[]{i, i+1});}}}